package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.*;
import com.deere.democlient.util.JodaConverter;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

public class CreateFieldMapLayer extends AbstractApiBase {

    /*
    * Assuming ORG_ID has been activated for given DEFINITION_ID
    *
    */

    public static final String DEFINITION_ID = "Enter contribution definition ID here";
    public static final int ORG_ID = 0; // Assign Organization ID here
    public static final String FIELD_ID = "Enter field ID here";

    public static final String FILE_RESOURCE_PATH = "src/main/resources/Field7FiveManagementZones.png"; // Enter your file resource path
    public static final String FILE_NAME = "Field7FiveManagementZones.png"; // Enter file name here
    public static final String MIME_TYPE = "image/png"; // Enter mime type here


    public static void main(String[] arg) throws IOException {

        CreateFieldMapLayer fieldMapLayer = new CreateFieldMapLayer();

        ContributedMapLayerSummary contributedMapLayerSummary = createContributedMapLayerSummaryWith(DEFINITION_ID, ORG_ID, FIELD_ID);
        String mapLayerSummaryLocation = fieldMapLayer.createMapLayerSummary(contributedMapLayerSummary);

        ContributedMapLayer mapLayer = CreateMapLayerWith(ORG_ID);
        String mapLayerLocation = fieldMapLayer.createMapLayer(mapLayerSummaryLocation, mapLayer);

        FileResource fileResource = createFileResourceWith(ORG_ID);
        String fileResourceLocation = fieldMapLayer.createFileResource(mapLayerLocation, fileResource);

        fieldMapLayer.uploadFileResource(fileResourceLocation, FILE_RESOURCE_PATH);

//        fieldMapLayer.deleteMapLayerSummary(mapLayerSummaryLocation);

    }

    private void uploadFileResource(String fileResourceLocation, String fileResourcePath) {
        final RestRequest restRequest = oauthRequestTo(fileResourceLocation)
                .method("PUT")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", "application/octet-stream"))
                .body(com.google.common.io.Files.newInputStreamSupplier(new java.io.File(fileResourcePath)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());
    }

    private String createFileResource(String mapLayerLocation, FileResource fileResource) {
        final RestRequest restRequest = oauthRequestTo(mapLayerLocation + "/fileResources")
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(fileResource)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());

        String location = restResponse.getHeaderFields().valueOf("Location");
        System.out.println("New file resource Link : " + location);
        return location;
    }

    private static ContributedMapLayer CreateMapLayerWith(int orgId) {
        ContributedMapLayer mapLayer = new ContributedMapLayer();
        ArrayList<Link> links = newArrayList(linkWith("owningOrganization", baseUri + "organizations/" + orgId));
        mapLayer.setLinks(links);
        mapLayer.setExtent(new MapExtent(0.0, 0.0, 0.0, 0.0)); // Enter extent here
        MapLegend legends = new MapLegend();
        legends.setUnitId("Pounds");
        // Enter your ranges here, following is just an example
        legends.setRanges(newArrayList(new MapLegendItem("250","#DC143C", 0.33, 0.0, 100.0),
                new MapLegendItem("300","#0000CD", 0.33, 20.0, 80.0),
                new MapLegendItem("350","#FF7F00", 0.33, 0.0, 100.0)));
        mapLayer.setLegends(legends);
        mapLayer.setTitle("Set Map Layer title here");
        return mapLayer;
    }

    private String createMapLayer(String mapLayerSummaryLocation, ContributedMapLayer contributedMapLayer) {
        final RestRequest restRequest = oauthRequestTo(mapLayerSummaryLocation + "/mapLayers")
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(contributedMapLayer)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());

        String location = restResponse.getHeaderFields().valueOf("Location");
        System.out.println("New map layer Link : " + location);
        return location;
    }

    private String createMapLayerSummary(ContributedMapLayerSummary summary) {
        final RestRequest restRequest = oauthRequestTo(baseUri + "organizations/" + ORG_ID + "/fields/" + FIELD_ID + "/mapLayerSummaries")
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(summary)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());

        String location = restResponse.getHeaderFields().valueOf("Location");
        System.out.println("New map layer summary Link : " + location);
        return location;
    }

    private static ContributedMapLayerSummary createContributedMapLayerSummaryWith(String definitionId, int orgId, String fieldId) {
        ContributedMapLayerSummary summary = new ContributedMapLayerSummary();
        ArrayList<Link> links = newArrayList(linkWith("contributionDefinition", baseUri + "contributionDefinitions/" + definitionId),
                linkWith("owningOrganization", baseUri + "organizations/" + orgId),
                linkWith("targetResource", baseUri + "organizations/" + orgId + "/fields/" + fieldId));
        summary.setLinks(links);

        summary.setTitle("Enter Title here");
        summary.setText("Enter description text here");
        ArrayList<ContributedMetadata> contributedMetadata = newArrayList();
        ContributedMetadata data = new ContributedMetadata();
        data.setName("test name");
        data.setValue("test value");
        contributedMetadata.add(data);
        summary.setContributedMetadata(contributedMetadata);
        summary.setDateCreated(JodaConverter.marshal(new DateTime()));
        return summary;
    }

    private static FileResource createFileResourceWith(int orgId) {
        FileResource fileResource = new FileResource();
        fileResource.setLinks(newArrayList(linkWith("owningOrganization", baseUri + "organizations/" + orgId)));
        fileResource.setMimeType(MIME_TYPE);
        fileResource.setContributedMetadata(newArrayList(new ContributedMetadata("filename", FILE_NAME)));
        return fileResource;
    }


    private void deleteMapLayerSummary(String mapLayerSummaryLocation) {
        System.out.println("Deleting: "+ mapLayerSummaryLocation);
        final RestRequest restRequest = oauthRequestTo(mapLayerSummaryLocation)
                .method("DELETE")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());
    }
}
