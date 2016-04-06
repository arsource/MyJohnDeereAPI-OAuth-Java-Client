
package com.deere.api.axiom.generated.v3;

/**
 * <p>Java class for cropType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cropType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.deere.com/v3}resource">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="densityFactor" type="{http://api.deere.com/v3}density" minOccurs="0"/>
 *         &lt;element name="standardPayableMoisture" type="{http://api.deere.com/v3}moisture" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

public class CropType
    extends Resource
{

    private final static long serialVersionUID = 1L;
    protected String name;
    protected Density densityFactor;
    protected Moisture standardPayableMoisture;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the densityFactor property.
     * 
     * @return
     *     possible object is
     *     {@link Density }
     *     
     */
    public Density getDensityFactor() {
        return densityFactor;
    }

    /**
     * Sets the value of the densityFactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Density }
     *     
     */
    public void setDensityFactor(Density value) {
        this.densityFactor = value;
    }

    /**
     * Gets the value of the standardPayableMoisture property.
     * 
     * @return
     *     possible object is
     *     {@link Moisture }
     *     
     */
    public Moisture getStandardPayableMoisture() {
        return standardPayableMoisture;
    }

    /**
     * Sets the value of the standardPayableMoisture property.
     * 
     * @param value
     *     allowed object is
     *     {@link Moisture }
     *     
     */
    public void setStandardPayableMoisture(Moisture value) {
        this.standardPayableMoisture = value;
    }

}
