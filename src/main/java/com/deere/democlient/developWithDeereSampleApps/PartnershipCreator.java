package com.deere.democlient.developWithDeereSampleApps;

import com.deere.api.axiom.generated.v3.Partnership;
import com.deere.democlient.brokers.PartnershipBroker;
import com.deere.democlient.brokers.PermissionsBroker;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class PartnershipCreator {
    private static final String myOrgUri = "https://apicert.soa-proxy.deere.com/platform/organizations/263770";
    private static final List<String> permissionTypesOfInterest = Arrays.asList("allMachines", "productionAgronomicDetailData",
            "prescriptionFiles", "setupFiles", "sendFilesToMachineCapabilities", "locationHistory", "remoteDisplayAccess");

    public static void main(String[] args) {
        PartnershipCreator partnershipCreator = new PartnershipCreator();
        partnershipCreator.initiatePartnershipAndRequestPermissions(); //use when authenticated as yourself
        partnershipCreator.initiatePartnershipAndAssignPermissions(); //use when your desired partner has authenticated
    }

    private void initiatePartnershipAndRequestPermissions() {
        PartnershipBroker partnershipBroker = new PartnershipBroker();
        String location = partnershipBroker.createPartnership(myOrgUri, "requestingAccess@example.com");
        Partnership partnership = partnershipBroker.getPartnershipDetails(location);

        String incomingPartnershipUri = partnershipBroker.linksFrom(partnership).get("contactInvitation").getUri();
        Partnership incomingPartnership = partnershipBroker.getPartnershipDetails(incomingPartnershipUri);
        String incomingPermissionsUri = partnershipBroker.linksFrom(incomingPartnership).get("permissions").getUri();

        PermissionsBroker permissionsBroker = new PermissionsBroker();
        permissionsBroker.requestPermissions(incomingPermissionsUri, permissionTypesOfInterest);
    }

    private void initiatePartnershipAndAssignPermissions() {
        PartnershipBroker partnershipBroker = new PartnershipBroker();
        String location = partnershipBroker.createPartnership(myOrgUri, "grantingAccess@example.com");
        Partnership partnership = partnershipBroker.getPartnershipDetails(location);

        String outgoingPermissionsUri = partnershipBroker.linksFrom(partnership).get("permissions").getUri();

        PermissionsBroker permissionsBroker = new PermissionsBroker();
        permissionsBroker.assignPermissions(outgoingPermissionsUri, permissionTypesOfInterest);
    }
}
