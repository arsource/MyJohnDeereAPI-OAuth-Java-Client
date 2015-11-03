package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.*;
import com.deere.api.pagination.CollectionPage;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_EMPTY;

public class Upload extends AbstractApiBase {
    private String userOrganizations;
    private Link fileUploadLink;
    private String newFileLocation;

    public static void main(String[] arg ) throws Exception{
        final RestRequest apiCatalogRequest = oauthRequestTo(baseUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse apiCatalogResponse = apiCatalogRequest.fetchResponse();

        Upload upload = new Upload();

        upload.getCurrentUser();
        upload.getUserOrganizations();
        upload.addFile();
        //uploadFile();
        upload.uploadFileInCHunks();
        upload.deleteUploadedFile();

    }

    public void getCurrentUser() {
        //assume(apiCatalog.get("currentUser"), isNotNull());

        final RestRequest currentUserRequest = oauthRequestTo(apiCatalog.get("currentUser").getUri())
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse currentUserResponse = currentUserRequest.fetchResponse();

        final Resource currentUser = read(currentUserResponse).as(User.class);

        userOrganizations = linksFrom(currentUser).get("organizations").getUri();
    }

    public void getUserOrganizations() {
        //assume(userOrganizations, isNotNull());

        final RestRequest userOrganizationsRequest = oauthRequestTo(userOrganizations)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse userOrganizationsResponse = userOrganizationsRequest.fetchResponse();

        final CollectionPage<Organization> organizations =
                read(userOrganizationsResponse).as(new TypeReference<CollectionPage<Organization>>() {});

        fileUploadLink = linksFrom(organizations.get(0)).get("uploadFile");
    }

    public void addFile() throws IOException {
       // assume(fileUploadLink, isNotNull());

        final File apiFile = new File();
        apiFile.setName("greatFileFromDeere.zip");

        final ObjectMapper objectMapper = initObjectMapper();
        final RestRequest newFileRequest = oauthRequestTo(fileUploadLink.getUri())
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(objectMapper.writeValueAsBytes(apiFile)))
                .build();

        final RestResponse newFileResponse = newFileRequest.fetchResponse();

        //checkThat("POST Response", newFileResponse.getResponseCode(), isEqualTo(CREATED));
        newFileLocation = newFileResponse.getHeaderFields().valueOf("Location");
    }

    public void uploadFile() {
        final RestRequest fileUploadRequest = oauthRequestTo(newFileLocation)
                .method("PUT")
                .addHeader(new HttpHeader("Accept", "application/vnd.deere.axiom.v3+json"))
                .addHeader(new HttpHeader("Content-Type", "application/zip"))
                .body(Files.newInputStreamSupplier(new java.io.File("/wdtTestFile.zip")))
                .build();

        final RestResponse fileUploadResponse = fileUploadRequest.fetchResponse();
    }

    public void uploadFileInCHunks() {
        FileInputStream is = null;
        long contentLength =0;
        try {
            java.io.File file = new java.io.File("src/main/resources/wdtTestFile.zip");
            is = new FileInputStream(file);
            contentLength = file.length();
            int bufferSize = 10000;
            byte[] bytesPortion = new byte[bufferSize];
            int byteNumber = 0;
            while (is.read(bytesPortion, 0, bufferSize) != -1) {
                String contentRange = "bytes " + Integer.toString(byteNumber);
                long bytesLeft = contentLength - byteNumber;
                System.out.println("Bytes Left: " + bytesLeft);
                if (bytesLeft < bufferSize) {
                    bytesPortion = Arrays.copyOf(bytesPortion, (int) bytesLeft);
                    bufferSize = (int) bytesLeft;
                }

                byteNumber += bytesPortion.length;
                contentRange += "-" + (byteNumber - 1) + "/" + contentLength;

                InputSupplier<ByteArrayInputStream> body =     ByteStreams.newInputStreamSupplier(bytesPortion);

                final RestRequest fileUploadRequest = oauthRequestTo(newFileLocation)
                        .method("PUT")
                        .addHeader(new HttpHeader("Accept", "application/vnd.deere.axiom.v3+json"))
                        .addHeader(new HttpHeader("Content-Type", "application/zip"))
                        .addHeader(new HttpHeader("Content-Range", contentRange))
                        .body(body)
                        .build();

                final RestResponse fileUploadResponse = fileUploadRequest.fetchResponse();
                System.out.println("done");


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }


        Files.newInputStreamSupplier(new java.io.File("/wdtTestFile.zip"));


        //checkThat("PUT Response", fileUploadResponse.getResponseCode(), isEqualTo(NO_CONTENT));
    }

    public void deleteUploadedFile() {

        final RestRequest deleteFileRequest = oauthRequestTo(newFileLocation)
                .method("DELETE")
                .addHeader(new HttpHeader("Accept", "application/vnd.deere.axiom.v3+json"))
                .build();

        final RestResponse deleteResponse = deleteFileRequest.fetchResponse();
        //checkThat("DELETE Response", deleteResponse.getResponseCode(), isEqualTo(NO_CONTENT));
    }
}
