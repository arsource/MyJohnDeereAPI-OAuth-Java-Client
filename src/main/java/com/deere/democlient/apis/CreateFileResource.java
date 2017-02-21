package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.FileResource;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;

public class CreateFileResource extends AbstractApiBase{
    public String createFileResource(String mapLayerLocation, FileResource fileResource) {
        final RestRequest restRequest = oauthRequestTo(mapLayerLocation + "/fileResources")
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(fileResource)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());

        String location = restResponse.getHeaderFields().valueOf("Location");
        System.out.println("New file resource Link : " + location);
        return location;
    }

    public void uploadFileResource(String fileResourceLocation, String fileResourcePath) {
        final RestRequest restRequest = oauthRequestTo(fileResourceLocation)
                .method("PUT")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", "application/octet-stream"))
                .body(com.google.common.io.Files.newInputStreamSupplier(new java.io.File(fileResourcePath)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());
    }
}
