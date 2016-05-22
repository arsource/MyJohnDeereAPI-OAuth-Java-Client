package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.Organization;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.HttpStatus;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;

import static org.hamcrest.core.IsEqual.equalTo;

public class OrganizationDetailsBroker extends AbstractApiBase {

    public Organization getOrgDetails(String orgDetailsUri) {
        RestRequest request = oauthRequestTo(orgDetailsUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();
        final RestResponse response = request.fetchResponse();
        System.out.println("Response code: " + response.getResponseCode());
        assertThat("Get Org Details call is successful", response.getResponseCode(), equalTo(HttpStatus.OK));
        return read(response).as(Organization.class);
    }

    public void updateOrgDetails(String orgDetailsUri, Organization organization) {
        RestRequest request = oauthRequestTo(orgDetailsUri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(organization)))
                .build();
        final RestResponse response = request.fetchResponse();
        System.out.println("Response code: " + response.getResponseCode());
        assertThat("postFileDetails response is successful", response.getResponseCode(), equalTo(HttpStatus.OK));
    }
}
