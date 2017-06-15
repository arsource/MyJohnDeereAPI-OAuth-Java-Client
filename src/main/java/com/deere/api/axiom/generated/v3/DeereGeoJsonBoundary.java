package com.deere.api.axiom.generated.v3;

import org.geojson.GeometryCollection;

public class DeereGeoJsonBoundary extends Resource {
    protected String name;
    protected boolean active;
    protected boolean irrigated;
    protected GeometryCollection geometryCollection;

    public DeereGeoJsonBoundary() {}

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIrrigated() {
        return irrigated;
    }

    public void setIrrigated(boolean irrigated) {
        this.irrigated = irrigated;
    }

    public GeometryCollection getGeometryCollection() {
        return geometryCollection;
    }

    public void setGeometryCollection(GeometryCollection geometryCollection) {
        this.geometryCollection = geometryCollection;
    }
}