package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.*;
import com.deere.democlient.brokers.GenericListBroker;
import com.deere.democlient.brokers.OrganizationDetailsBroker;
import com.deere.democlient.util.*;

import java.util.List;
import java.util.Set;

public class ClientFarmFieldExtract extends AbstractApiBase {

    public static void main(String[] args) {
        //Replace the following URI with that of your desired organization
        final String orgUri = "https://apicert.soa-proxy.deere.com/platform/organizations/{target organization ID}";
        org.junit.Assert.assertFalse(orgUri.contains("{target organization ID}"));

        Organization org = new OrganizationDetailsBroker().getOrgDetails(orgUri);
        System.out.println("Org Name: " + org.getName() + "\n");

        String fieldsUri = getFieldsUriWithClientAndFarmEmbeds(org);
        List<Field> fields = new GenericListBroker().getList(fieldsUri, Field.class);
        ClientFarmFieldTree clientFarmFieldTree = new ClientFarmFieldTree();
        clientFarmFieldTree.addFieldsWithEmbeddedClientAndFarm(fields);
        printClientFarmFieldStructure(clientFarmFieldTree);
    }

    private static String getFieldsUriWithClientAndFarmEmbeds(Organization organization) {
        return organization.getLinks().stream()
                .filter(link -> link.getRel().equals("fields"))
                .findFirst().orElseThrow(() -> new RuntimeException("You do not have access to the field list for this organization"))
                .getUri().concat(";count=100?embed=clients,farms");
    }

    private static void printClientFarmFieldStructure(ClientFarmFieldTree clientFarmFieldTree) {
        for (Client client : clientFarmFieldTree.getClients()) {
            printClient(client);
            Set<Farm> farms = clientFarmFieldTree.getFarmsForClient(client);
            for (Farm farm : farms) {
                printFarm(farm);
                Set<Field> fields = clientFarmFieldTree.getFieldsForFarm(farm);
                for (Field field : fields) {
                    printField(field);
                }
            }
        }
    }

    private static void printClient(Client client) {
        System.out.println("Client: " + client.getId() + " - " + client.getName());
    }

    private static void printFarm(Farm farm) {
        System.out.println("-----Farm: " + farm.getId() + " - " + farm.getName());
    }

    private static void printField(Field field) {
        System.out.println("----------Field: " + field.getId() + " - " + field.getName());
    }
}
