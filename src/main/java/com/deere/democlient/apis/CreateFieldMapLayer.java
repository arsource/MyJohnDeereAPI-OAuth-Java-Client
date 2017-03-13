package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.*;
import com.deere.democlient.brokers.ResourceDeletionBroker;
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

    public static final String FILE_NAME_SHAPEFILE = "shapefile_with_colors.zip"; // Enter shapefile name here
    public static final String MIME_TYPE_SHAPEFILE = "application/zip";

    public static final String FILE_NAME_PNG = "Field7FiveManagementZones.png"; // Enter PNG file name here
    public static final String MIME_TYPE_PNG = "image/png";

    public static final String SHAPEFILE_RESOURCE_PATH = "src/main/resources/" + FILE_NAME_SHAPEFILE; // Enter your file resource path
    public static final String PNG_RESOURCE_PATH = "src/main/resources/" + FILE_NAME_PNG; // Enter your file resource path


    public static void main(String[] arg) throws IOException {
        //Shapefile as MapLayer
        CreateMapLayerWith(DEFINITION_ID, ORG_ID, FILE_NAME_SHAPEFILE, MIME_TYPE_SHAPEFILE, SHAPEFILE_RESOURCE_PATH);

        //PNG as MapLayer
        CreateMapLayerWith(DEFINITION_ID, ORG_ID, FILE_NAME_PNG, MIME_TYPE_PNG, PNG_RESOURCE_PATH);
    }

    private static void CreateMapLayerWith(String definitionId, int orgId, String fileName, String mimeType, String fileResourcePath) {
        CreateFieldMapLayer fieldMapLayer = new CreateFieldMapLayer();
        CreateFileResource fileResourceHelper = new CreateFileResource();

        ContributedMapLayerSummary contributedMapLayerSummary = createContributedMapLayerSummaryWith(definitionId, orgId);
        String mapLayerSummaryLocation = fieldMapLayer.createMapLayerSummary(contributedMapLayerSummary);

        ContributedMapLayer mapLayer = CreateMapLayerWith(orgId);
        String mapLayerLocation = fieldMapLayer.createMapLayer(mapLayerSummaryLocation, mapLayer);

        FileResource fileResource = createFileResourceWith(orgId, fileName, mimeType);
        String fileResourceLocation = fileResourceHelper.createFileResource(mapLayerLocation, fileResource);

        fileResourceHelper.uploadFileResource(fileResourceLocation, fileResourcePath);

        // Delete Map layer and summary
//        ResourceDeletionBroker deleteResource = new ResourceDeletionBroker();
//        deleteResource.deleteResource(mapLayerSummaryLocation);
    }

    private static ContributedMapLayer CreateMapLayerWith(int orgId) {
        ContributedMapLayer mapLayer = new ContributedMapLayer();
        ArrayList<Link> links = newArrayList(linkWith("owningOrganization", baseUri + "organizations/" + orgId));
        mapLayer.setLinks(links);
        mapLayer.setExtent(new MapExtent(41.6662524, 41.6686728, -93.1500448, -93.145828)); // Enter extent here
        MapLegend legends = new MapLegend();
        legends.setUnitId("Pounds");
        // Enter your ranges here, following is just an example
        legends.setRanges(newArrayList(
                new MapLegendItem("250","#F55E28", 0.20, 0.0, 100.0),
                new MapLegendItem("275","#FDA12E", 0.20, 0.0, 100.0),
                new MapLegendItem("300","#D5BD30", 0.20, 0.0, 100.0),
                new MapLegendItem("325","#83CB2E", 0.20, 0.0, 100.0),
                new MapLegendItem("350","#3CD72E", 0.20, 0.0, 100.0)));
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

    private static ContributedMapLayerSummary createContributedMapLayerSummaryWith(String definitionId, int orgId) {
        ContributedMapLayerSummary summary = new ContributedMapLayerSummary();
        ArrayList<Link> links = newArrayList(linkWith("contributionDefinition", baseUri + "contributionDefinitions/" + definitionId),
                linkWith("owningOrganization", baseUri + "organizations/" + orgId));
        summary.setLinks(links);

        summary.setTitle("Enter Title here");
        summary.setText("Enter description text here");
        ArrayList<ContributedMetadata> metadata = newArrayList();
        ContributedMetadata data = new ContributedMetadata();
        data.setName("test name");
        data.setValue("test value");
        metadata.add(data);
        summary.setMetadata(metadata);
        summary.setDateCreated(JodaConverter.marshal(new DateTime()));
        return summary;
    }

    private static FileResource createFileResourceWith(int orgId, String fileName, String mimeType) {
        FileResource fileResource = new FileResource();
        fileResource.setLinks(newArrayList(linkWith("owningOrganization", baseUri + "organizations/" + orgId)));
        fileResource.setMimeType(mimeType);
        fileResource.setMetadata(newArrayList(new ContributedMetadata("filename", fileName)));
        return fileResource;
    }
}
