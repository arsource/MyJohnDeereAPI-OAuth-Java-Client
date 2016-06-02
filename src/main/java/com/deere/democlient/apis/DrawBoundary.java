package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.Field;
import com.deere.api.axiom.generated.v3.Organization;
import com.deere.democlient.brokers.GenericListBroker;
import com.deere.democlient.brokers.OrganizationListBroker;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by gw87612 on 01 Jun 2016.
 */
public class DrawBoundary extends AbstractApiBase {
    public static void main(String[] arg) {
        DrawBoundary drawBoundary = new DrawBoundary();
        drawBoundary.drawBoundary();
    }

	private void drawBoundary() {
        Organization selectedOrganization = getOrganizationSelection();
        String boundaryRequestURL = getFieldUrlForOrganization(selectedOrganization);

        Field selectedField = getFieldSelection(boundaryRequestURL);
        boundaryRequestURL = getBoundaryUrlForField(selectedField);

        final RestRequest boundaryDrawRequest = oauthRequestTo(boundaryRequestURL)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", "application/vnd.deere.axiom.v3.geo+json"))
                .body(ByteStreams.newInputStreamSupplier(GeoJSONtoDeereJSON().getBytes()))
                .build();
        final RestResponse boundaryDrawResponse = boundaryDrawRequest.fetchResponse();

        System.out.println(boundaryDrawResponse.getResponseMessage());
    }
	
	private Organization getOrganizationSelection() {
        Organization[] organizations = new OrganizationListBroker().getOwnedOrganizations().toArray(new Organization[0]);
        System.out.println("Found " + organizations.length + " organizations.");

        Organization organization = null;
        if(organizations.length < 1)
            System.exit(-1);
        else if(organizations.length == 1) {
            System.out.println("Selected organization " + organizations[0].getName() + ".");
            organization = organizations[0];
        }
        else {
            for (int i = 0; i < organizations.length; i++)
                System.out.println(" [" + (i + 1) + "] " + organizations[i].getName());
            final int organizationSelection = getSelection("Select an organization: ", organizations.length);
            System.out.println("Selected organization " + organizations[organizationSelection - 1].getName() + ".");
            organization = organizations[organizationSelection - 1];
        }
        return organization;
    }
	
	private Field getFieldSelection(String boundaryRequestURL) {
        Field[] fields = new GenericListBroker().getList(boundaryRequestURL, Field.class).toArray(new Field[0]);
        System.out.println("\nFound " + fields.length + " fields.");

        Field field = null;
        if(fields.length < 1)
            System.exit(-1);
        else if(fields.length == 1) {
            System.out.println("Selected field " + fields[0].getName() + ".");
            field = fields[0];
        }
        else {
            for(int i = 0; i < fields.length; i++)
                System.out.println(" [" + (i+1) + "] " + fields[i].getName());
            final int fieldSelection = getSelection("Select a field: ", fields.length);
            System.out.println("Selected field " + fields[fieldSelection-1].getName() + ".");
            field = fields[fieldSelection-1];
        }
        return field;
    }
	
    private String getFieldUrlForOrganization(Organization organization) {
        return organization.getLinks().stream().filter(link -> link.getRel().equals("fields"))
                .findFirst().orElseThrow(() -> new RuntimeException("No access to the field list for this organization")).getUri();
    }

    private String getBoundaryUrlForField(Field field) {
        return field.getLinks().stream().filter(link -> link.getRel().equals("boundaries"))
                .findFirst().orElseThrow(() -> new RuntimeException("No access to the boundary list for this field")).getUri();
    }

    private static int getSelection(String message, int limit) {
        System.out.print(message);
        while(true) {
            try {
                int selection = new Scanner(System.in).nextInt();

                if(selection > limit || selection < 1)
                    throw new RuntimeException("Invalid input.");

                return selection;
            } catch (RuntimeException e) {
                System.out.println("Invalid selection.");
                System.out.print(message);
            }
        }
    }

    private static String GeoJSONFromFile() {
        try {
            final String path = new File("").getAbsolutePath() + "\\src\\main\\resources\\DrawBoundary.txt";
            return new Scanner(new File(path)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(-1);
        }
        return "";
    }

    private static String GeoJSONtoDeereJSON()
    {
        String GeoJSON = GeoJSONFromFile().split("coordinates")[1];
        GeoJSON = GeoJSON.split("}")[0];
        String result = "{ \"name\" : ";

        System.out.print("\nEnter shape name: ");
        result += "\"" + new Scanner(System.in).next() + "\",\n\"active\" : true,\n";

        System.out.print("Irrigated:\n [1] True\n [2] False\n");
        int selection = getSelection("Enter selection: ", 2);
        result += "\"irrigated\" : " + (selection==1?"true":"false") + ",\n";

        result += "\"geometryCollection\": {\n\"type\" : \"GeometryCollection\",\n";
        result += "\"geometries\" : [{\"type\": \"Polygon\",\n\"coordinates";
        result += GeoJSON;

        return result + "}]}}";
    }
}
