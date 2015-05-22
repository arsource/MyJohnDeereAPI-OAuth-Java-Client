package com.deere.democlient;

public class GetOAuthToken {


    public static void main(String[] args) throws Exception {
        final OAuthWorkFlow flow = new OAuthWorkFlow();

        flow.retrieveApiCatalogToEstablishOAuthProviderDetails();

        flow.getRequestToken();
        flow.authorizeRequestToken();
        flow.exchangeRequestTokenForAccessToken();
    }

}
