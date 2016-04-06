
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkPlan extends Resource {

    private Long priority;
    private String workPlanId = UUID.randomUUID().toString();
    private String boundaryId;
    private String notes;
    private String comments;
   /* private DateTime plannedStartDate;
    private DateTime plannedEndDate;*/
    private Double plannedArea;
    private List<Operation> operations = new ArrayList();
    private List<WorkAssignment> workAssignments = new ArrayList();
    private List<WorkQuestionAssignment> workQuestionAssignments = new ArrayList();
    private List<JobGuidanceLineReference> guidanceLines = new ArrayList();
    private Job job;
    private OperationType type = OperationType.UNKNOWN;

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getWorkPlanId() {
        return workPlanId;
    }

    public void setWorkPlanId(String workPlanId) {
        this.workPlanId = workPlanId;
    }

    public String getBoundaryId() {
        return boundaryId;
    }

    public void setBoundaryId(String boundaryId) {
        this.boundaryId = boundaryId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getPlannedArea() {
        return plannedArea;
    }

    public void setPlannedArea(Double plannedArea) {
        this.plannedArea = plannedArea;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<WorkAssignment> getWorkAssignments() {
        return workAssignments;
    }

    public void setWorkAssignments(List<WorkAssignment> workAssignments) {
        this.workAssignments = workAssignments;
    }

    public List<WorkQuestionAssignment> getWorkQuestionAssignments() {
        return workQuestionAssignments;
    }

    public void setWorkQuestionAssignments(List<WorkQuestionAssignment> workQuestionAssignments) {
        this.workQuestionAssignments = workQuestionAssignments;
    }

    public List<JobGuidanceLineReference> getGuidanceLines() {
        return guidanceLines;
    }

    public void setGuidanceLines(List<JobGuidanceLineReference> guidanceLines) {
        this.guidanceLines = guidanceLines;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }
}
