package com.deere.api.axiom.generated.v3;

import java.util.List;

public class GroupedNotificationsResponse extends Resource {

    protected String beforeId;
    protected String afterId;
    protected List<GroupedNotifications> groupedNotifications;

    public String getBeforeId() {
        return beforeId;
    }

    public void setBeforeId(String beforeId) {
        this.beforeId = beforeId;
    }

    public String getAfterId() {
        return afterId;
    }

    public void setAfterId(String afterId) {
        this.afterId = afterId;
    }

    public List<GroupedNotifications> getGroupedNotifications() {
        return groupedNotifications;
    }

    public void setGroupedNotifications(List<GroupedNotifications> groupedNotifications) {
        this.groupedNotifications = groupedNotifications;
    }
}
