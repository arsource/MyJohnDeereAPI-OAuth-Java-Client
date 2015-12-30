package com.deere.api.axiom.generated.v3;

public class LocationHistory extends Resource {

    private Point point;
    private String eventTimestamp;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }
}
