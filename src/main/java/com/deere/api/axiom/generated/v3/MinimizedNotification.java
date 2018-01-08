package com.deere.api.axiom.generated.v3;

import org.joda.time.DateTime;

import java.util.List;

public class MinimizedNotification {
    protected List<AdditionalDetail> additionalDetails;
    protected String notificationState;
    protected Long targetResourceOrgId;

    protected DateTime dateCreated;

    public List<AdditionalDetail> getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(List<AdditionalDetail> additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getNotificationState() {
        return notificationState;
    }

    public void setNotificationState(String notificationState) {
        this.notificationState = notificationState;
    }

    public Long getTargetResourceOrgId() {
        return targetResourceOrgId;
    }

    public void setTargetResourceOrgId(Long targetResourceOrgId) {
        this.targetResourceOrgId = targetResourceOrgId;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
