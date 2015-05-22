package com.deere.api.axiom.generated.v3;

public class LocationHistory extends Resource {

    private Point point;
    private long eventTimestamp;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }
}
