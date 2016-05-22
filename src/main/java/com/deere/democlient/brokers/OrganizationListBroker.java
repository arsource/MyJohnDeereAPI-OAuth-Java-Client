package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.Organization;
import com.deere.api.pagination.CollectionPage;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class OrganizationListBroker extends AbstractApiBase {

    public List<Organization> getOwnedOrganizations() {
        CurrentUserBroker currentUserBroker = new CurrentUserBroker();
        String currentUserOrgsUri = linksFrom(currentUserBroker.getCurrentUserDetails()).get("organizations").getUri();
        return getOrganizations(currentUserOrgsUri);
    }

    //fyi - beware of loading a potentially large list into memory
    public List<Organization> getOwnedAndPartnerOrganizations() {
        return getOrganizations(apiCatalog.get("organizations").getUri());
    }

    private List<Organization> getOrganizations(String uri) {
        List<Organization> organizations = new ArrayList<Organization>();
        while (uri != null) {
            CollectionPage<Organization> page = getOrganizationsSinglePage(uri);
            organizations.addAll(page);
            uri = page.getNextPage() == null? null : page.getNextPage().toString();
        }
        return organizations;
    }

    private CollectionPage<Organization> getOrganizationsSinglePage(String uri) {
        RestRequest request = oauthRequestTo(uri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse response = request.fetchResponse();
        final CollectionPage<Organization> organizations =
                read(response).as(new TypeReference<CollectionPage<Organization>>() {});
        return organizations;
    }
}
