
package com.deere.api.axiom.generated.v3;

import com.deere.rest.OAuthClient;

import java.util.UUID;

public class StatusUpdate extends Resource {

    private String statusUpdateId = UUID.randomUUID().toString();
//    private DateTime recordedAt;
    private JobStatus status;
    private JobStatusSource source;
    private String sourceGuid;
    private JobWorkRecord jobWorkRecord;
    private AxiomUser user;
    private OAuthClient client;

    public String getStatusUpdateId() {
        return statusUpdateId;
    }

    public void setStatusUpdateId(String statusUpdateId) {
        this.statusUpdateId = statusUpdateId;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public JobStatusSource getSource() {
        return source;
    }

    public void setSource(JobStatusSource source) {
        this.source = source;
    }

    public String getSourceGuid() {
        return sourceGuid;
    }

    public void setSourceGuid(String sourceGuid) {
        this.sourceGuid = sourceGuid;
    }

    public JobWorkRecord getJobWorkRecord() {
        return jobWorkRecord;
    }

    public void setJobWorkRecord(JobWorkRecord jobWorkRecord) {
        this.jobWorkRecord = jobWorkRecord;
    }

    public AxiomUser getUser() {
        return user;
    }

    public void setUser(AxiomUser user) {
        this.user = user;
    }

    public OAuthClient getClient() {
        return client;
    }

    public void setClient(OAuthClient client) {
        this.client = client;
    }
}
