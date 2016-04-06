
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkOrder extends Resource {

    private String jobWorkOrderId = UUID.randomUUID().toString();
/*
    private DateTime startDate;
    private DateTime endDate;
*/
    private Integer priority;
    private String fieldId;
    private List<JobGuidanceLineReference> guidanceLines = new ArrayList();
    private List<JobWorkRecord> jobWorkRecords = new ArrayList();
    private List<WorkAssignment> workAssignments = new ArrayList();
    private Job job;

    public String getJobWorkOrderId() {
        return jobWorkOrderId;
    }

    public void setJobWorkOrderId(String jobWorkOrderId) {
        this.jobWorkOrderId = jobWorkOrderId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public List<JobGuidanceLineReference> getGuidanceLines() {
        return guidanceLines;
    }

    public void setGuidanceLines(List<JobGuidanceLineReference> guidanceLines) {
        this.guidanceLines = guidanceLines;
    }

    public List<JobWorkRecord> getJobWorkRecords() {
        return jobWorkRecords;
    }

    public void setJobWorkRecords(List<JobWorkRecord> jobWorkRecords) {
        this.jobWorkRecords = jobWorkRecords;
    }

    public List<WorkAssignment> getWorkAssignments() {
        return workAssignments;
    }

    public void setWorkAssignments(List<WorkAssignment> workAssignments) {
        this.workAssignments = workAssignments;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
