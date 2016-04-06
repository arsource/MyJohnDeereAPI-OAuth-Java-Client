
package com.deere.api.axiom.generated.v3;

public class WorkRecord extends Resource {

/*
    private DateTime createdDate = Clock.now();
    private DateTime lastModifiedDate;
    private DateTime startedDate;
*/
    private JobStatusSource source = JobStatusSource.GROWER_OPS;
    private String sourceGuid;
    private JobStatus status = JobStatus.NOT_STARTED;
    private WorkAssignment workAssignment;
    private String workAssignmentGuid;
    private Job job;
    private AxiomUser user;
    private boolean historical;

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

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public WorkAssignment getWorkAssignment() {
        return workAssignment;
    }

    public void setWorkAssignment(WorkAssignment workAssignment) {
        this.workAssignment = workAssignment;
    }

    public String getWorkAssignmentGuid() {
        return workAssignmentGuid;
    }

    public void setWorkAssignmentGuid(String workAssignmentGuid) {
        this.workAssignmentGuid = workAssignmentGuid;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public AxiomUser getUser() {
        return user;
    }

    public void setUser(AxiomUser user) {
        this.user = user;
    }

    public boolean isHistorical() {
        return historical;
    }

    public void setHistorical(boolean historical) {
        this.historical = historical;
    }
}
