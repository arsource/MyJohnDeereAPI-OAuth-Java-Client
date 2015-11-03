package com.deere.democlient.apis;


import com.deere.api.axiom.generated.v3.*;
import com.deere.api.pagination.CollectionPage;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

public class MaMos extends AbstractApiBase {

    private String machinesLink;
    private String userOrganizations;
    private String machineLink;

    public static void main(String[] arg) throws IOException {
        final RestRequest apiCatalogRequest = oauthRequestTo(baseUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse apiCatalogResponse = apiCatalogRequest.fetchResponse();
        MaMos maMos = new MaMos();
        maMos.getCurrentUser();
        maMos.getUserOrganizations();
        maMos.getMachinesByOrg();
        maMos.getMachineEngineHours();
        maMos.getMachineHoursOfOperation();
        maMos.getMachineLocationHistory();
        maMos.getMachineMeasurements();
        maMos.getMachineDeviceStateReport();
    }

    public void getCurrentUser() {

        final RestRequest currentUserRequest = oauthRequestTo(apiCatalog.get("currentUser").getUri())
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse currentUserResponse = currentUserRequest.fetchResponse();

        final Resource currentUser = read(currentUserResponse).as(User.class);

        userOrganizations = linksFrom(currentUser).get("organizations").getUri();

    }

    public void getUserOrganizations() {

        final RestRequest userOrganizationsRequest = oauthRequestTo(userOrganizations)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse userOrganizationsResponse = userOrganizationsRequest.fetchResponse();

        final CollectionPage<Organization> organizations =
                read(userOrganizationsResponse).as(new TypeReference<CollectionPage<Organization>>() {
                });

        machinesLink = linksFrom(organizations.get(0)).get("machines").getUri();
    }


    public void getMachinesByOrg() {

        final RestRequest machinesRequest = oauthRequestTo(machinesLink)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse machinesResponse = machinesRequest.fetchResponse();

        final CollectionPage<Machine> machines =
                read(machinesResponse).as(new TypeReference<CollectionPage<Machine>>() {
                });

        machineLink = linksFrom(machines.get(0)).get("self").getUri();
    }

    public void getMachineEngineHours() {

        final RestRequest machineRequest = oauthRequestTo(machineLink+"/engineHours")
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse machineResponse = machineRequest.fetchResponse();
        EngineHour eh = read(machineResponse).as(EngineHour.class);
        System.out.println("EngineHours="+machineResponse.getResponseCode());
    }

    public void getMachineHoursOfOperation() {

        final RestRequest machineRequest = oauthRequestTo(machineLink+"/hoursOfOperation")
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse machineResponse = machineRequest.fetchResponse();

        //HourOfOperation hoo = read(machineResponse).as(HourOfOperation.class);
        CollectionPage<HourOfOperation> hoo = read(machineResponse).as(new TypeReference<CollectionPage<HourOfOperation>>() {});

        System.out.println("HourOfOperation="+machineResponse.getResponseCode());
    }

    public void getMachineLocationHistory() {

        final RestRequest machineRequest = oauthRequestTo(machineLink+"/locationHistory")
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse machineResponse = machineRequest.fetchResponse();

        CollectionPage<LocationHistory> lh = read(machineResponse).as(new TypeReference<CollectionPage<LocationHistory>>() {});

        System.out.println("LocationHistory="+machineResponse.getResponseCode());
    }

    public void getMachineMeasurements() {

        final RestRequest machineRequest = oauthRequestTo(machineLink+"/machineMeasurements")
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse machineResponse = machineRequest.fetchResponse();
        CollectionPage<MachineMeasurement> mm = read(machineResponse).as(new TypeReference<CollectionPage<MachineMeasurement>>() {});
        System.out.println("MachineMeasurements="+machineResponse.getResponseCode());
    }

    public void getMachineDeviceStateReport() {

        final RestRequest machineRequest = oauthRequestTo(machineLink+"/deviceStateReports")
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse machineResponse = machineRequest.fetchResponse();
        System.out.println("DeviceStateReports="+machineResponse.getResponseCode());
    }
}
