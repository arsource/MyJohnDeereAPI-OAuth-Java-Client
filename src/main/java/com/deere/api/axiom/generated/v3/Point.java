package com.deere.api.axiom.generated.v3;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


public class Point {
    private double lat;
    private double lon;
    private Reading altitude;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Reading getAltitude() {
        return altitude;
    }

    public void setAltitude(Reading altitude) {
        this.altitude = altitude;
    }
}
