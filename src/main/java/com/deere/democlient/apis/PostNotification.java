package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.EventAssociation;
import com.deere.api.axiom.generated.v3.EventStatus;
import com.deere.api.axiom.generated.v3.NotificationEvent;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;

public class PostNotification extends AbstractApiBase {

    /*
    * Assuming ORG_ID has been activated for given DEFINITION_ID
    *
    */

    public static final String DEFINITION_ID = "Enter contribution definition ID here";
    public static final int ORG_ID = 0; // Assign Organization ID here
    public static final String FIELD_ID = "Enter field ID here";

    public static void main(String[] arg) {
        PostNotification postNotification = new PostNotification();

        String location = postNotification.postNotification(createNotificationEvent());
        postNotification.getNotificationStatusFor(location);

        String location2 = postNotification.postNotification2Legged(createNotificationEvent());
        postNotification.getNotificationStatusFor(location2);

    }

    private void getNotificationStatusFor(String location) {
        String eventLocation = location.replaceFirst("/", "");
        final RestRequest restRequest = oauthRequestTo(baseUri + eventLocation)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .build();

        final RestResponse response = restRequest.fetchResponse();
        System.out.println("Response code: " + response.getResponseCode());

        final EventStatus status = read(response).as(EventStatus.class);
        System.out.println("Event Status: "+ status.getEventStatusCode());
    }


    private String postNotification(NotificationEvent event) {

        final RestRequest restRequest = oauthRequestTo(baseUri + "notificationEvents")
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(event)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());

        String location = restResponse.getHeaderFields().valueOf("Location");

        System.out.println("New event Link : " + location);
        return location;
    }

    private String postNotification2Legged(NotificationEvent event) {
        System.out.println("Making a two legged call ....");
        final RestRequest restRequest = oauthRequest2LeggedTo(baseUri + "notificationEvents")
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(event)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());

        String location = restResponse.getHeaderFields().valueOf("Location");

        System.out.println("New event Link : " + location);
        return location;
    }

    private static NotificationEvent createNotificationEvent() {
        NotificationEvent event = new NotificationEvent();
        event.setLinks(Lists.newArrayList(linkWith("source", "/contributionDefinitions/" + DEFINITION_ID)));

        EventAssociation eventAssociation = new EventAssociation();
        eventAssociation.setLinks(Lists.newArrayList(linkWith("targetResource", "/organizations/" + ORG_ID + "/fields/" + FIELD_ID)));
        event.setEventAssociation(eventAssociation);

        event.setEventType("SCOUTING");
        event.setTitle("Enter Title here");
        event.setText("Enter description text here");
        event.setSeverity("MEDIUM");
        return event;
    }
}
