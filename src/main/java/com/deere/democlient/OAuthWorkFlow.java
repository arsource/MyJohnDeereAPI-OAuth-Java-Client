package com.deere.democlient;

import com.deere.api.axiom.generated.v3.ApiCatalog;
import com.deere.api.axiom.generated.v3.Link;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static com.deere.democlient.ApiCredentials.CLIENT;
import static com.deere.rest.ContentExchanger.read;
import static com.deere.rest.RestRequestBuilder.request;
import static java.lang.String.format;

public class OAuthWorkFlow {

    private DefaultOAuthProvider provider = null;
    private String authUri;
    private String verifier;
    private DefaultOAuthConsumer consumer;

    public void retrieveApiCatalogToEstablishOAuthProviderDetails() {
        final RestRequest apiCatalogRequest = request()
                .method("GET")
                .baseUri("https://apicert.soa-proxy.deere.com/platform/")
                .addHeader(new HttpHeader("Accept", "application/vnd.deere.axiom.v3+json"))
                .oauthClient(CLIENT)
                .build();

        final RestResponse apiCatalogResponse = apiCatalogRequest.fetchResponse();

        final ApiCatalog apiCatalog = read(apiCatalogResponse).as(ApiCatalog.class);

        final Map<String, Link> links = linksFrom(apiCatalog);

        provider = new ProxyAwareOAuthProvider(links);

    }

    public void getRequestToken() throws Exception {
        consumer = new DefaultOAuthConsumer(CLIENT.getKey(), CLIENT.getSecret());
        authUri = provider.retrieveRequestToken(consumer, "oob");
    }

    public void authorizeRequestToken() throws IOException {
        System.out.println(format("Please provide the verifier from %s", authUri));
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        verifier = reader.readLine();
    }

    public void exchangeRequestTokenForAccessToken() throws Exception {
        provider.retrieveAccessToken(consumer, verifier);
        System.out.println(format("Token: %s\nToken Secret: %s", consumer.getToken(), consumer.getTokenSecret()));
    }

    private ImmutableMap<String, Link> linksFrom(final ApiCatalog apiCatalog) {
        return Maps.uniqueIndex(apiCatalog.getLinks(),
                                new Function<Link, String>() {
                                    @Override public String apply(final Link input) {
                                        return input.getRel();
                                    }
                                });
    }

}
