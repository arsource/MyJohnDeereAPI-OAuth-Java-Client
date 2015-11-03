package com.deere.democlient.brokers;

import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.HttpStatus;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class ResourceDeletionBroker extends AbstractApiBase {
    public void deleteResource(String uri) {
        final RestRequest deletionRequest = oauthRequestTo(uri)
                .method("DELETE")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        RestResponse response = deletionRequest.fetchResponse();
        assertThat("delete resource response", response.getResponseCode(), equalTo(HttpStatus.NO_CONTENT));
    }
}
