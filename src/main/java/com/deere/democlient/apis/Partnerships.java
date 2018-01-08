package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.*;
import com.deere.api.pagination.CollectionPage;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

public class Partnerships extends AbstractApiBase {

    private String userOrganizations;
    private String fromPartnershipLink;
    private String contactInvitationLink;
    private String newPartnerLink;

    public static void main(String[] arg) throws IOException {
        final RestRequest apiCatalogRequest = oauthRequestTo(baseUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse apiCatalogResponse = apiCatalogRequest.fetchResponse();

        Partnerships partnerships = new Partnerships();
        partnerships.getCurrentUser();
        partnerships.fromPartnershipLink = partnerships.extractLinkFromOrganizations("self");
        partnerships.setupPartnership();
        partnerships.getPartnerContactInvitation();
        partnerships.requestPermission();
        partnerships.deletePartnership();
    }

    public void setupPartnership() throws IOException {

        Partnership partnership = new Partnership();

        Link toPartnerShipLink = new Link();
        toPartnerShipLink.setRel("toPartnership");
        toPartnerShipLink.setUri("mailto:dcconference2014@gmail.com");

        partnership.getLinks().add(toPartnerShipLink);

        Link fromPartnerShipLink = new Link();
        fromPartnerShipLink.setRel("fromPartnership");
        fromPartnerShipLink.setUri(fromPartnershipLink);

        partnership.getLinks().add(fromPartnerShipLink);

        final ObjectMapper objectMapper = getObjectMapper();

        final RestRequest newPartnerRequest = oauthRequestTo(basePartnershipUri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(objectMapper.writeValueAsBytes(partnership)))
                .build();

        final RestResponse newPartnerResponse = newPartnerRequest.fetchResponse();
        newPartnerLink = newPartnerResponse.getHeaderFields().valueOf("Location");
        System.out.println("New Partnership Link : " + newPartnerLink);
    }

    public void getPartnerContactInvitation() {
        final RestRequest partnershipRequest = oauthRequestTo(newPartnerLink)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse partnershipResponse = partnershipRequest.fetchResponse();

        final Resource partnership = read(partnershipResponse).as(Partnership.class);

        contactInvitationLink = linksFrom(partnership).get("contactInvitation").getUri();
        System.out.println("New Partnership Contact Invitation Link : " + contactInvitationLink);

    }

    public void requestPermission() throws IOException {

        Permissions permissions = new Permissions();

        Permission permission = new Permission();

        permission.setType("productionAgronomicDetailData");

        permission.setStatus("requested");

        permissions.getPermissions().add(permission);

        String permissionsPath = "/permissions";

        final ObjectMapper objectMapper = getObjectMapper();

       /* StringBuilder partnerRequestBody = new StringBuilder().append("{" +
                "  \"permissions\":[" +
                "    {" +
                "      \"type\":\"productionAgronomicDetailData\"," +
                "      \"status\":\"requested\"" +
                "    }" +
                "  ]" +
                "}");*/

        final RestRequest newPartnerPermissionRequest = oauthRequestTo(contactInvitationLink + permissionsPath)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(objectMapper.writeValueAsBytes(permissions)))
                .build();

        final RestResponse newPartnerPermissionResponse = newPartnerPermissionRequest.fetchResponse();
        System.out.println("New Partnership Permission Done with code : " + newPartnerPermissionResponse.getResponseCode());
    }

    public void deletePartnership() {

        final RestRequest deletePartnershipRequest = oauthRequestTo(newPartnerLink)
                .method("DELETE")
                .addHeader(new HttpHeader("Accept", "application/vnd.deere.axiom.v3+json"))
                .build();

        final RestResponse deleteResponse = deletePartnershipRequest.fetchResponse();
        System.out.println("New Partnership Delete Done with code : " + deleteResponse.getResponseCode());
    }
}
