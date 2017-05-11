package com.deere.democlient.brokers;


import com.deere.api.axiom.generated.v3.Token;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;

public class CurrentTokenBroker extends AbstractApiBase {

    public Token getCurrentTokenDetails() {
        RestRequest request = oauthRequestTo(getCurrentUserPath())
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();
        final RestResponse response = request.fetchResponse();
        return read(response).as(Token.class);
    }
    private String getCurrentUserPath() {
        return apiCatalog.get("currentToken").getUri();
    }
}
