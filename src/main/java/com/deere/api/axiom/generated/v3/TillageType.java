
package com.deere.api.axiom.generated.v3;


public class TillageType
    extends Resource
{

    private final static long serialVersionUID = 1L;
    protected String vrDomainId;
    protected String name;
    protected String guid;
    protected Integer tillageTypeId;
//    protected DateTime lastModifiedTs;

    /**
     * Gets the value of the vrDomainId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrDomainId() {
        return vrDomainId;
    }

    /**
     * Sets the value of the vrDomainId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrDomainId(String value) {
        this.vrDomainId = value;
    }

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
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * Gets the value of the tillageTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getTillageTypeId() {
        return tillageTypeId;
    }

    /**
     * Sets the value of the tillageTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTillageTypeId(Integer value) {
        this.tillageTypeId = value;
    }

}
