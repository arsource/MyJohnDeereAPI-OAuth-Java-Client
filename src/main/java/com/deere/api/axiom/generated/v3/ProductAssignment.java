
package com.deere.api.axiom.generated.v3;

import java.io.Serializable;



public class ProductAssignment
    extends Resource
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected MeasurementAsString rate;
    protected MeasurementAsDouble depth;
    protected ReferenceProduct product;

    /**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementAsString }
     *     
     */
    public MeasurementAsString getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementAsString }
     *     
     */
    public void setRate(MeasurementAsString value) {
        this.rate = value;
    }

    /**
     * Gets the value of the depth property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementAsDouble }
     *     
     */
    public MeasurementAsDouble getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementAsDouble }
     *     
     */
    public void setDepth(MeasurementAsDouble value) {
        this.depth = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceProduct }
     *     
     */
    public ReferenceProduct getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceProduct }
     *     
     */
    public void setProduct(ReferenceProduct value) {
        this.product = value;
    }

}
