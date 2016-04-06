
package com.deere.api.axiom.generated.v3;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for seedingOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="seedingOperation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.deere.com/v3}operation">
 *       &lt;sequence>
 *         &lt;element name="cropType" type="{http://api.deere.com/v3}cropType" minOccurs="0"/>
 *         &lt;element name="shapeFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seedAssignment" type="{http://api.deere.com/v3}productAssignment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="prescriptionAssignments" type="{http://api.deere.com/v3}prescriptionAssignment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class SeedingOperation
    extends Operation
{

    private final static long serialVersionUID = 1L;
    protected CropType cropType;
    protected String shapeFileName;
    @XmlElement(name = "seedAssignment")
    protected List<ProductAssignment> seedAssignments;
    protected List<PrescriptionAssignment> prescriptionAssignments;

    /**
     * Gets the value of the cropType property.
     * 
     * @return
     *     possible object is
     *     {@link CropType }
     *     
     */
    public CropType getCropType() {
        return cropType;
    }

    /**
     * Sets the value of the cropType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CropType }
     *     
     */
    public void setCropType(CropType value) {
        this.cropType = value;
    }

    /**
     * Gets the value of the shapeFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShapeFileName() {
        return shapeFileName;
    }

    /**
     * Sets the value of the shapeFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShapeFileName(String value) {
        this.shapeFileName = value;
    }

    /**
     * Gets the value of the seedAssignments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the seedAssignments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeedAssignments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductAssignment }
     * 
     * 
     */
    public List<ProductAssignment> getSeedAssignments() {
        if (seedAssignments == null) {
            seedAssignments = new ArrayList<ProductAssignment>();
        }
        return this.seedAssignments;
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
