package com.deere.api.axiom.generated.v3;

import java.util.List;

public class MapLegend extends Resource{
    protected String unitId;
    protected List<MapLegendItem> ranges;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public List<MapLegendItem> getRanges() {
        return ranges;
    }

    public void setRanges(List<MapLegendItem> ranges) {
        this.ranges = ranges;
    }
}
