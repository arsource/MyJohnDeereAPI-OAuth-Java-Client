package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.Organization;

import java.util.List;

public class OrganizationListBroker extends GenericListBroker {

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
        return getList(uri, Organization.class);
    }
}
