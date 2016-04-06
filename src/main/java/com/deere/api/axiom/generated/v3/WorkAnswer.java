
package com.deere.api.axiom.generated.v3;

import java.util.UUID;

public class WorkAnswer extends Resource {

    private String workAnswerId = UUID.randomUUID().toString();
    private JobWorkRecord jobWorkRecord;
    private WorkQuestion workQuestion;
    private String uom;
    private String value;

    public String getWorkAnswerId() {
        return workAnswerId;
    }

    public void setWorkAnswerId(String workAnswerId) {
        this.workAnswerId = workAnswerId;
    }

    public JobWorkRecord getJobWorkRecord() {
        return jobWorkRecord;
    }

    public void setJobWorkRecord(JobWorkRecord jobWorkRecord) {
        this.jobWorkRecord = jobWorkRecord;
    }

    public WorkQuestion getWorkQuestion() {
        return workQuestion;
    }

    public void setWorkQuestion(WorkQuestion workQuestion) {
        this.workQuestion = workQuestion;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
