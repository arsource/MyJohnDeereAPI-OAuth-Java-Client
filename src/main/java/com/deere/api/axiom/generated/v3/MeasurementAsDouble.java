
package com.deere.api.axiom.generated.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for measurementAsDouble complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="measurementAsDouble">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.deere.com/v3}abstractMeasurement">
 *       &lt;sequence>
 *         &lt;element name="valueAsDouble" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "measurementAsDouble", propOrder = {
    "valueAsDouble"
})
public class MeasurementAsDouble
{

    private final static long serialVersionUID = 1L;
    protected Double valueAsDouble;

    /**
     * Gets the value of the valueAsDouble property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValueAsDouble() {
        return valueAsDouble;
    }

    /**
     * Sets the value of the valueAsDouble property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValueAsDouble(Double value) {
        this.valueAsDouble = value;
    }


}
