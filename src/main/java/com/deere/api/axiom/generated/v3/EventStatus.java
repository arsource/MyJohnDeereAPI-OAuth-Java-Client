
package com.deere.api.axiom.generated.v3;

public class EventStatus
    extends Resource
{

    private final static long serialVersionUID = 1L;
    protected String eventId;
    protected String eventStatusCode;
    protected Long expectedNotificationCount;
    protected Long actualNotificationCount;

    /**
     * Gets the value of the eventId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventId(String value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the eventStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventStatusCode() {
        return eventStatusCode;
    }

    /**
     * Sets the value of the eventStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventStatusCode(String value) {
        this.eventStatusCode = value;
    }

    /**
     * Gets the value of the expectedNotificationCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExpectedNotificationCount() {
        return expectedNotificationCount;
    }

    /**
     * Sets the value of the expectedNotificationCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExpectedNotificationCount(Long value) {
        this.expectedNotificationCount = value;
    }

    /**
     * Gets the value of the actualNotificationCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualNotificationCount() {
        return actualNotificationCount;
    }

    /**
     * Sets the value of the actualNotificationCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualNotificationCount(Long value) {
        this.actualNotificationCount = value;
    }



}
