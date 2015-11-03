package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.Link;
import com.deere.api.axiom.generated.v3.Partnership;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.HttpStatus;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class PartnershipBroker extends AbstractApiBase {
    private static final String FROM_PARTNERSHIP = "fromPartnership";
    private static final String TO_PARTNERSHIP = "toPartnership";

    public Partnership getPartnershipDetails(String partnershipDetailsUri) {
        RestRequest request = oauthRequestTo(partnershipDetailsUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();
        final RestResponse response = request.fetchResponse();
//        System.out.println("Response code: " + response.getResponseCode());
        assertThat("Get Partnership Details call is successful", response.getResponseCode(), equalTo(HttpStatus.OK));
        return read(response).as(Partnership.class);
    }

    public String createPartnership(Partnership partnership) {
        RestRequest request = oauthRequestTo(getPartnershipsUri())
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(partnership)))
                .build();
        final RestResponse response = request.fetchResponse();
//        System.out.println("Response code: " + response.getResponseCode());
        assertThat("Create partnership response is successful", response.getResponseCode(), equalTo(HttpStatus.CREATED));
        return response.getHeaderFields().valueOf("Location");
    }

    public String createPartnership(String senderOrganizationUri, String recipientEmailAddress) {
        Partnership partnership = createPartnershipDto(senderOrganizationUri, recipientEmailAddress);
        return createPartnership(partnership);
    }

    private static Partnership createPartnershipDto(String senderOrganizationUri, String recipientEmailAddress) {
        Link fromPartnershipLink = linkWith(FROM_PARTNERSHIP, senderOrganizationUri);
        Link toPartnershipLink = linkWith(TO_PARTNERSHIP, "mailto:" + recipientEmailAddress);
        Partnership partnership = new Partnership();
        partnership.setLinks(Arrays.asList(fromPartnershipLink, toPartnershipLink));
        return partnership;
    }

    private String getPartnershipsUri() {
        return apiCatalog.get("partnerships").getUri();
    }
}
