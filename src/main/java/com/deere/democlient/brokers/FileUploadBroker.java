package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.File;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.HttpStatus;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class FileUploadBroker extends AbstractApiBase {

    public static File constructFileMetaDataObject(String name) {
        final File apiFile = new File();
        apiFile.setName(name);
        return apiFile;
    }

    public String postFileDetailsAndUploadContent(String orgFilesUri, File fileDetails, java.io.File file) {
        String location = postFileDetails(orgFilesUri, fileDetails);
        uploadFileContent(location, file);
        return location;
    }

    private String postFileDetails(String uri, File fileMetaData) {
        final RestRequest newFileRequest = oauthRequestTo(uri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(fileMetaData)))
                .build();

        final RestResponse response = newFileRequest.fetchResponse();

//        System.out.println("Response code: " + response.getResponseCode());
        assertThat("postFileDetails response", response.getResponseCode(), equalTo(HttpStatus.CREATED));
        assertThat("postFileDetails location", response.getHeaderFields().valueOf("Location"), notNullValue());
//        System.out.println("New file uri: " + response.getHeaderFields().valueOf("Location"));
        return response.getHeaderFields().valueOf("Location");
    }

    private void uploadFileContent(String uri, java.io.File file) {
        final RestRequest fileUploadRequest = oauthRequestTo(uri)
                .method("PUT")
                .addHeader(new HttpHeader("Accept", "application/vnd.deere.axiom.v3+json"))
                .addHeader(new HttpHeader("Content-Type", "application/zip"))
                .body(Files.newInputStreamSupplier(file))
                .build();

        final RestResponse fileUploadResponse = fileUploadRequest.fetchResponse();
        assertThat("uploadFile response", fileUploadResponse.getResponseCode(), equalTo(HttpStatus.NO_CONTENT));
    }
}
