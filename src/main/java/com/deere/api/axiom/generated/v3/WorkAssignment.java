
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkAssignment extends Resource {

    private String workAssignmentId = UUID.randomUUID().toString();
//    private DateTime createdDate = Clock.now();
    private WorkPlan workPlan;
    private String workPlanGuid;
    private String machineId;
    private AxiomUser operator;
    private Long priority;
    private List<WorkAssignmentImplement> implementList = new ArrayList();
    private List<WorkRecord> workRecords = new ArrayList();
    private WorkOrder workOrder;
    private Operation operation;

    public String getWorkAssignmentId() {
        return workAssignmentId;
    }

    public void setWorkAssignmentId(String workAssignmentId) {
        this.workAssignmentId = workAssignmentId;
    }

    public WorkPlan getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(WorkPlan workPlan) {
        this.workPlan = workPlan;
    }

    public String getWorkPlanGuid() {
        return workPlanGuid;
    }

    public void setWorkPlanGuid(String workPlanGuid) {
        this.workPlanGuid = workPlanGuid;
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

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public List<WorkAssignmentImplement> getImplementList() {
        return implementList;
    }

    public void setImplementList(List<WorkAssignmentImplement> implementList) {
        this.implementList = implementList;
    }

    public List<WorkRecord> getWorkRecords() {
        return workRecords;
    }

    public void setWorkRecords(List<WorkRecord> workRecords) {
        this.workRecords = workRecords;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
