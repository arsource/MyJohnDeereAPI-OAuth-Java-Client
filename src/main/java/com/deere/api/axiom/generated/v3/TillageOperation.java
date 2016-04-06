
package com.deere.api.axiom.generated.v3;



import java.util.ArrayList;
import java.util.List;

public class TillageOperation extends Operation {

    protected TillageType tillageType;
//    protected MeasurementAsString depth;
    protected List<PrescriptionAssignment> prescriptionAssignments;


    public TillageType getTillageType() {
        return tillageType;
    }

    public void setTillageType(TillageType tillageType) {
        this.tillageType = tillageType;
    }

    public List<PrescriptionAssignment> getPrescriptionAssignments() {
        if (prescriptionAssignments == null) {
            prescriptionAssignments = new ArrayList<PrescriptionAssignment>();
        }
        return this.prescriptionAssignments;
    }

    public void setPrescriptionAssignments(List<PrescriptionAssignment> prescriptionAssignments) {
        this.prescriptionAssignments = prescriptionAssignments;
    }
}
