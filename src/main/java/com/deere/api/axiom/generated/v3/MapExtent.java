package com.deere.api.axiom.generated.v3;

public class MapExtent extends Resource{
    protected Double minimumLatitude;
    protected Double maximumLatitude;
    protected Double minimumLongitude;
    protected Double maximumLongitude;

    public MapExtent(Double minimumLatitude, Double maximumLatitude, Double minimumLongitude, Double maximumLongitude) {
        this.minimumLatitude = minimumLatitude;
        this.maximumLatitude = maximumLatitude;
        this.minimumLongitude = minimumLongitude;
        this.maximumLongitude = maximumLongitude;
    }

    public Double getMinimumLatitude() {
        return minimumLatitude;
    }

    public void setMinimumLatitude(Double minimumLatitude) {
        this.minimumLatitude = minimumLatitude;
    }

    public Double getMaximumLatitude() {
        return maximumLatitude;
    }

    public void setMaximumLatitude(Double maximumLatitude) {
        this.maximumLatitude = maximumLatitude;
    }

    public Double getMinimumLongitude() {
        return minimumLongitude;
    }

    public void setMinimumLongitude(Double minimumLongitude) {
        this.minimumLongitude = minimumLongitude;
    }

    public Double getMaximumLongitude() {
        return maximumLongitude;
    }

    public void setMaximumLongitude(Double maximumLongitude) {
        this.maximumLongitude = maximumLongitude;
    }
}
