package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.ApiCatalog;
import com.deere.api.axiom.generated.v3.File;
import com.deere.api.axiom.generated.v3.Link;
import com.deere.api.pagination.CollectionPage;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.collect.ImmutableMap;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static com.deere.rest.HttpStatus.OK;
import static com.deere.rest.ResponseCodeMatcher.hasResponseCode;
import static com.google.common.io.ByteStreams.copy;
import static com.google.common.io.ByteStreams.nullOutputStream;
import static java.lang.Math.min;
import static java.lang.String.format;
import static java.security.MessageDigest.getInstance;

public class Download extends AbstractApiBase {

    private static Map<String,Link> apiCatalog;
    private CollectionPage<File> files;
    private String firstFileSelfUri;
    private byte[] md5FromSinglePieceDownload;
    private String filename;

    public static void main(String[] arg ) throws Exception{
        final RestRequest apiCatalogRequest = oauthRequestTo(baseUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse apiCatalogResponse = apiCatalogRequest.fetchResponse();

        Download download = new Download();

        apiCatalog = download.linksFrom(download.read(apiCatalogResponse).as(ApiCatalog.class));

        download.getFiles();

        download.retrieveMetadataForFile();

        download.downloadFileContentsAndComputeMd5();
        download.downloadFileInPiecesAndComputeMd5();
    }


    public void getFiles() throws Exception {
        final RestRequest filesRequest = oauthRequestTo(apiCatalog.get("files").getUri())
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse filesResponse = filesRequest.fetchResponse();

        files = read(filesResponse).as(new TypeReference<CollectionPage<File>>() {});

    }

    public void retrieveMetadataForFile() {

        final ImmutableMap<String,Link> linksFromFirstFile = linksFrom(files.get(1));

        firstFileSelfUri = linksFromFirstFile.get("self").getUri();

        final RestRequest fileDetails = oauthRequestTo(firstFileSelfUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse fileDetailsResponse = fileDetails.fetchResponse();

        final File firstFileDetails = read(fileDetailsResponse).as(File.class);

        filename = firstFileDetails.getName();
    }

    public void downloadFileContentsAndComputeMd5() throws NoSuchAlgorithmException, IOException {

        final RestRequest fileDetails = oauthRequestTo(firstFileSelfUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", "application/zip"))
                .build();

        final RestResponse fileDetailsResponse = fileDetails.fetchResponse();

        checkFilenameInContentDispositionHeader(fileDetailsResponse);

        final DigestOutputStream byteDigest = new DigestOutputStream(nullOutputStream(), MessageDigest.getInstance("md5"));

        copy(fileDetailsResponse.getBody(), byteDigest);

        md5FromSinglePieceDownload = byteDigest.getMessageDigest().digest();
    }

   public void downloadFileInPiecesAndComputeMd5() throws NoSuchAlgorithmException, IOException {
        final int fileSize = makeHeadRequestToGetFileSize();

        final int numberOfChunks = 2;
        final int chunkSize = (fileSize / numberOfChunks) - 1;
        final DigestOutputStream byteDigest = new DigestOutputStream(nullOutputStream(), getInstance("md5"));

        getChunkFromStartAndRecurse(0, chunkSize, fileSize, byteDigest);

        //checkThat("md5 digest", byteDigest.getMessageDigest().digest(), isEqualTo(md5FromSinglePieceDownload));
    }

    private void checkFilenameInContentDispositionHeader(final RestResponse fileDetailsResponse) {
        final String contentDispositionHeader = "Content-Disposition";
        //checkThat("Content-Disposition header set?", fileDetailsResponse.getHeaderFields().contains(contentDispositionHeader), isTrue());

        if (fileDetailsResponse.getHeaderFields().contains(contentDispositionHeader)) {
            final String contentDisposition = fileDetailsResponse.getHeaderFields().valueOf(contentDispositionHeader);
            final java.util.regex.Matcher matcher = contentDispositionPattern.matcher(contentDisposition);
            if (matcher.matches()) {
               // checkThat("filename as reported by API", matcher.group(2), isEqualTo(filename));
            }
        }
    }

    private void getChunkFromStartAndRecurse(final int start,
                                             final int chunkSize,
                                             final int fileSize,
                                             final DigestOutputStream byteDigest) throws IOException {
        final int maxRange = fileSize - 1;
        final int end = min(start + chunkSize, maxRange);
        final RestRequest rangeRequest = oauthRequestTo(firstFileSelfUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", "application/zip"))
                .addHeader(new HttpHeader("Range", format("bytes=%d-%d", start, end)))
                .build();

        final RestResponse rangeResponse = rangeRequest.fetchResponse();

        checkFilenameInContentDispositionHeader(rangeResponse);

        copy(rangeResponse.getBody(), byteDigest);

        if (start + chunkSize < maxRange) {
            getChunkFromStartAndRecurse(start + chunkSize + 1, chunkSize, fileSize, byteDigest);
        }
    }

    private int makeHeadRequestToGetFileSize() {
        final RestRequest headForFile = oauthRequestTo(firstFileSelfUri)
                .method("HEAD")
                .addHeader(new HttpHeader("Accept", "application/octet-stream"))
                .build();
        final RestResponse headRes = headForFile.fetchResponse();
        //assertOk(headRes);
        if (!hasResponseCode(OK).matches(headRes)) {
            firstFileSelfUri = null;
            //fail(format("HEAD request to %s returned bad response code", firstFileSelfUri));
        }
        //checkThat("Content-Length header", headRes.getHeaderFields().contains("Content-Length"), isTrue());
        return Integer.valueOf(headRes.getHeaderFields().valueOf("Content-Length"));
    }
}
