package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.ContributedMapLayerSummary;
import com.deere.api.axiom.generated.v3.Link;
import com.deere.api.axiom.generated.v3.Metadata;
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

    public static void main(String[] arg) throws IOException {
        CreateFieldMapLayer fieldMapLayer = new CreateFieldMapLayer();

        ContributedMapLayerSummary contributedMapLayerSummary = createContributedMapLayerSummaryWith(DEFINITION_ID, ORG_ID);
        fieldMapLayer.createMapLayerSummary(contributedMapLayerSummary);
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
        ArrayList<Metadata> metadata = newArrayList();
        Metadata data = new Metadata();
        data.setName("test name");
        data.setValue("test value");
        metadata.add(data);
        summary.setMetadata(metadata);
        summary.setDateCreated(JodaConverter.marshal(new DateTime()));
        return summary;
    }
}
