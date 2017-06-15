package com.deere.democlient.apis;

import com.deere.rest.RestRequestBuilder;

import static com.deere.democlient.apis.AbstractApiBase.oauthRequestTo;

public class AbstractApiBaseWrapper {
    public RestRequestBuilder oauthRequestToWrapper(String baseUri) {
        return oauthRequestTo(baseUri);
    }
}
