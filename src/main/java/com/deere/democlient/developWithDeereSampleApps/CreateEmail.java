package com.deere.democlient.developWithDeereSampleApps;

import com.deere.api.axiom.generated.v3.Organization;
import com.deere.api.axiom.generated.v3.User;
import com.deere.democlient.ContributionCredentials;
import com.deere.democlient.brokers.CurrentUserBroker;
import com.deere.democlient.util.EmailWriter;
import com.deere.democlient.util.OrganizationUI;

public class CreateEmail extends EmailWriter {
    public static void main(String[] args) throws Exception {
        OrganizationUI organizationUI = new OrganizationUI();
        CurrentUserBroker currentUserBroker = new CurrentUserBroker();
        EmailWriter emailWriter = new EmailWriter();
        User user = currentUserBroker.getCurrentUserDetails();
        Organization organization = organizationUI.getOrganizationFromUser();
        emailWriter.openEmail(emailWriter.emailBody(user.getAccountName(), user.getGivenName() + " " + user.getFamilyName(), organization.getId(), organization.getName(), ContributionCredentials.CONTRIBUTION_PRODUCT_GUID, ContributionCredentials.CONTRIBUTION_PRODUCT_NAME));
    }
}