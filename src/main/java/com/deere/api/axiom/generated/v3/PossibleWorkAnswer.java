
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PossibleWorkAnswer extends Resource {

    private String possibleWorkAnswerId = UUID.randomUUID().toString();
    private WorkQuestion workQuestion;
    private List<PossibleWorkAnswerText> possibleWorkAnswerTexts = new ArrayList();

    public String getPossibleWorkAnswerId() {
        return possibleWorkAnswerId;
    }

    public void setPossibleWorkAnswerId(String possibleWorkAnswerId) {
        this.possibleWorkAnswerId = possibleWorkAnswerId;
    }

    public WorkQuestion getWorkQuestion() {
        return workQuestion;
    }

    public void setWorkQuestion(WorkQuestion workQuestion) {
        this.workQuestion = workQuestion;
    }

    public List<PossibleWorkAnswerText> getPossibleWorkAnswerTexts() {
        return possibleWorkAnswerTexts;
    }

    public void setPossibleWorkAnswerTexts(List<PossibleWorkAnswerText> possibleWorkAnswerTexts) {
        this.possibleWorkAnswerTexts = possibleWorkAnswerTexts;
    }
}
