
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JobWorkRecord extends Resource {

    private String jobWorkRecordId = UUID.randomUUID().toString();
    /*private DateTime startDate;
    private DateTime endDate;*/
    private List<StatusUpdate> statusUpdates = new ArrayList();
    private List<WorkAnswer> workAnswers = new ArrayList();
    private JobStatus status = JobStatus.NOT_STARTED;
    private WorkAssignment workAssignment;
    private String workAssignmentGuid;
    private WorkOrder workOrder;
    private Job job;
    private String machineId;
    private AxiomUser operator;

    public String getJobWorkRecordId() {
        return jobWorkRecordId;
    }

    public void setJobWorkRecordId(String jobWorkRecordId) {
        this.jobWorkRecordId = jobWorkRecordId;
    }

    public List<StatusUpdate> getStatusUpdates() {
        return statusUpdates;
    }

    public void setStatusUpdates(List<StatusUpdate> statusUpdates) {
        this.statusUpdates = statusUpdates;
    }

    public List<WorkAnswer> getWorkAnswers() {
        return workAnswers;
    }

    public void setWorkAnswers(List<WorkAnswer> workAnswers) {
        this.workAnswers = workAnswers;
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

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public AxiomUser getOperator() {
        return operator;
    }

    public void setOperator(AxiomUser operator) {
        this.operator = operator;
    }
}
