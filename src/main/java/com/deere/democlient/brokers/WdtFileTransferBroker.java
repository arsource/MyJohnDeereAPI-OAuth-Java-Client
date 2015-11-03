package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.FileTransfer;
import com.deere.api.axiom.generated.v3.Link;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;

import java.util.Arrays;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class WdtFileTransferBroker extends AbstractApiBase {

    public FileTransfer getFileTransfer(String uri) {
        RestRequest request = oauthRequestTo(uri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();
        final RestResponse response = request.fetchResponse();
        return read(response).as(FileTransfer.class);
    }

    public String transferFile(String uri, String fileUri, String machineUri) {
        return transferFile(uri, createTransferFileDto(fileUri, machineUri));
    }

    public String transferFile(String uri, FileTransfer fileTransfer) {
        final RestRequest request = oauthRequestTo(uri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(fileTransfer)))
                .build();

        final RestResponse response = request.fetchResponse();

//        System.out.println("Response code: " + response.getResponseCode());
//        assertThat("Transfer File response code is 201", response.getResponseCode(), equalTo(HttpStatus.CREATED));
//        assertThat("New file transfer location header is present", response.getHeaderFields().valueOf("Location"), notNullValue());
        return response.getHeaderFields().valueOf("Location");
    }

    public static FileTransfer createTransferFileDto(String fileUri, String machineUri) {
        Link fileLink = linkWith("file", fileUri);
        Link machineLink = linkWith("machine", machineUri);
        FileTransfer fileTransfer = new FileTransfer();
        fileTransfer.setLinks(Arrays.asList(fileLink, machineLink));
        return fileTransfer;
    }
}
