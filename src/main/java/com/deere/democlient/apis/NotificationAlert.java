package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.AdditionalDetail;
import com.deere.api.axiom.generated.v3.GroupedNotifications;
import com.deere.api.pagination.CollectionPage;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import org.codehaus.jackson.type.TypeReference;

import java.util.Map;
import java.util.stream.Collectors;

public class NotificationAlert extends AbstractApiBase{

    private String notificationEventLink;
    public static void main(String[] args) {
        NotificationAlert alert = new NotificationAlert();
        alert.getCurrentUser();
        alert.notificationEventLink = alert.extractLinkFromOrganizations("notifications");
        alert.getNotificationDTCAlerts();
    }

    public void getNotificationDTCAlerts() {

        final RestRequest notificationRequest = oauthRequestTo(notificationEventLink + "?eventTypes=DTC_ALERT")
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse notificationresponse = notificationRequest.fetchResponse();

        final CollectionPage<GroupedNotifications> groupedNotifications =
                read(notificationresponse).as(new TypeReference<CollectionPage<GroupedNotifications>>() {
                });
        GroupedNotifications notifications = groupedNotifications.stream().findFirst().get();
        System.out.println("Machine Alert Type = " + notifications.getEventType());
        System.out.println("Alert Severity = " + notifications.getSeverity());
        System.out.println("Alert text = " + notifications.getText());
        Map<String, String> additionalDetialsMap = notifications.getMinimizedNotifications().get(0)
                .getAdditionalDetails().stream().collect(Collectors.toMap(AdditionalDetail::getName, AdditionalDetail::getValue));

        System.out.println("Alert Time = " + additionalDetialsMap.get("alertCapturedTimestamp"));
        System.out.println("Machine Id = " + additionalDetialsMap.get("targetResourceId"));
        System.out.println("Machine Pin = " + additionalDetialsMap.get("machinePin"));
        System.out.println("Engine Hours = " + additionalDetialsMap.get("engineHours"));
        System.out.println("Machine Location = " + notifications.getGeometries());
    }
}
