
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;


public class ApplicationOperation
    extends Operation
{

    private final static long serialVersionUID = 1L;
    protected List<ProductAssignment> productAssignments;
    protected Boolean tankMix;
    protected List<PrescriptionAssignment> prescriptionAssignments;

    /**
     * Gets the value of the productAssignments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productAssignments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductAssignments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductAssignment }
     * 
     * 
     */
    public List<ProductAssignment> getProductAssignments() {
        if (productAssignments == null) {
            productAssignments = new ArrayList<ProductAssignment>();
        }
        return this.productAssignments;
    }

    /**
     * Gets the value of the tankMix property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTankMix() {
        return tankMix;
    }

    /**
     * Sets the value of the tankMix property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTankMix(Boolean value) {
        this.tankMix = value;
    }

    /**
     * Gets the value of the prescriptionAssignments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prescriptionAssignments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrescriptionAssignments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrescriptionAssignment }
     * 
     * 
     */
    public List<PrescriptionAssignment> getPrescriptionAssignments() {
        if (prescriptionAssignments == null) {
            prescriptionAssignments = new ArrayList<PrescriptionAssignment>();
        }
        return this.prescriptionAssignments;
    }

}
