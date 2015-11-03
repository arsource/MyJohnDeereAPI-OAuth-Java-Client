package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.User;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class CurrentUserBroker extends AbstractApiBase {

    public User getCurrentUserDetails() {
        RestRequest request = oauthRequestTo(getCurrentUserPath())
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();
        final RestResponse response = request.fetchResponse();
        return read(response).as(User.class);
    }

    private String getCurrentUserPath() {
        return apiCatalog.get("currentUser").getUri();
    }
}
