
package com.deere.api.axiom.generated.v3;

public class WorkQuestionAssignment extends Resource {

    private Operation operation;
    private WorkQuestion workQuestion;
    private WorkPlan workPlan;
    private boolean required;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public WorkQuestion getWorkQuestion() {
        return workQuestion;
    }

    public void setWorkQuestion(WorkQuestion workQuestion) {
        this.workQuestion = workQuestion;
    }

    public WorkPlan getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(WorkPlan workPlan) {
        this.workPlan = workPlan;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
