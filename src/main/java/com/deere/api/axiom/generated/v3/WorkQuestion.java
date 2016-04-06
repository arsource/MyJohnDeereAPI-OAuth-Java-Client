
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkQuestion extends Resource {

    private String workQuestionId = UUID.randomUUID().toString();
    private String vrDomainId;
    private WorkQuestionType type;
    private List<WorkQuestionText> workQuestionTexts = new ArrayList();
    private List<PossibleWorkAnswer> possibleWorkAnswers = new ArrayList();
    private List<OperationType> operationTypes = new ArrayList();

    public String getWorkQuestionId() {
        return workQuestionId;
    }

    public void setWorkQuestionId(String workQuestionId) {
        this.workQuestionId = workQuestionId;
    }

    public String getVrDomainId() {
        return vrDomainId;
    }

    public void setVrDomainId(String vrDomainId) {
        this.vrDomainId = vrDomainId;
    }

    public WorkQuestionType getType() {
        return type;
    }

    public void setType(WorkQuestionType type) {
        this.type = type;
    }

    public List<WorkQuestionText> getWorkQuestionTexts() {
        return workQuestionTexts;
    }

    public void setWorkQuestionTexts(List<WorkQuestionText> workQuestionTexts) {
        this.workQuestionTexts = workQuestionTexts;
    }

    public List<PossibleWorkAnswer> getPossibleWorkAnswers() {
        return possibleWorkAnswers;
    }

    public void setPossibleWorkAnswers(List<PossibleWorkAnswer> possibleWorkAnswers) {
        this.possibleWorkAnswers = possibleWorkAnswers;
    }

    public List<OperationType> getOperationTypes() {
        return operationTypes;
    }

    public void setOperationTypes(List<OperationType> operationTypes) {
        this.operationTypes = operationTypes;
    }
}
