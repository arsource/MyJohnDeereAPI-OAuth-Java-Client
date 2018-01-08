package com.deere.api.axiom.generated.v3;

import java.util.List;

public class GroupedNotifications {

    protected String geometries;
    protected String title;
    protected String text;
    protected String author;
    protected String severity;
    protected TimeRange timeRange;
    protected String sourceEvent;
    protected String eventType;
    protected List<MinimizedNotification> minimizedNotifications;
    protected ContributionDefinition contributionDefinition;
    protected ContributionProduct contributionProduct;

    public String getGeometries() {
        return geometries;
    }

    public void setGeometries(String geometries) {
        this.geometries = geometries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    public String getSourceEvent() {
        return sourceEvent;
    }

    public void setSourceEvent(String sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<MinimizedNotification> getMinimizedNotifications() {
        return minimizedNotifications;
    }

    public void setMinimizedNotifications(List<MinimizedNotification> minimizedNotifications) {
        this.minimizedNotifications = minimizedNotifications;
    }

    public ContributionDefinition getContributionDefinition() {
        return contributionDefinition;
    }

    public void setContributionDefinition(ContributionDefinition contributionDefinition) {
        this.contributionDefinition = contributionDefinition;
    }

    public ContributionProduct getContributionProduct() {
        return contributionProduct;
    }

    public void setContributionProduct(ContributionProduct contributionProduct) {
        this.contributionProduct = contributionProduct;
    }
}
