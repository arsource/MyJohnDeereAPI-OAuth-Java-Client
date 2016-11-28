
package com.deere.api.axiom.generated.v3;

public class NotificationEvent
    extends Resource
{

    private final static long serialVersionUID = 1L;
    protected EventAssociation eventAssociation;
    protected String geometry;
//    protected TimeRange timeRange;
    protected String title;
    protected String text;
    protected String severity;
//    protected List<AdditionalDetail> additionalDetails;
    protected String eventType;

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String value) {
        this.geometry = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String value) {
        this.severity = value;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String value) {
        this.eventType = value;
    }

    public EventAssociation getEventAssociation() {
        return eventAssociation;
    }

    public void setEventAssociation(EventAssociation eventAssociation) {
        this.eventAssociation = eventAssociation;
    }
}
