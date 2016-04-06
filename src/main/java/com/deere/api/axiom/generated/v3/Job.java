
package com.deere.api.axiom.generated.v3;

import com.deere.rest.OAuthClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Job extends Resource {

    private final static long serialVersionUID = 1L;
//    protected Field location;
    protected List<WorkRecord> workRecords;
    protected List<WorkAssignment> workAssignments;
//    protected List<GenericNote> jobNotes;
    protected List<Operation> operations;
    protected Organization owningOrganization;
    protected String notes;
    protected String cropYear;
    protected String status;
//    protected List<GuidanceLine> guidanceLines;
//    protected DateTime createdDate;
//    protected DateTime startedDate;
//    protected DateTime lastModifiedDate;
    protected String type;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<WorkRecord> getWorkRecords() {
        return workRecords;
    }

    public void setWorkRecords(List<WorkRecord> workRecords) {
        this.workRecords = workRecords;
    }

    public List<WorkAssignment> getWorkAssignments() {
        return workAssignments;
    }

    public void setWorkAssignments(List<WorkAssignment> workAssignments) {
        this.workAssignments = workAssignments;
    }

    public List<Operation> getOperations() {
        if (operations == null) {
            operations = new ArrayList<Operation>();
        }
        return this.operations;
    }
/*

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
*/

    public Organization getOwningOrganization() {
        return owningOrganization;
    }

    public void setOwningOrganization(Organization owningOrganization) {
        this.owningOrganization = owningOrganization;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCropYear() {
        return cropYear;
    }

    public void setCropYear(String cropYear) {
        this.cropYear = cropYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
