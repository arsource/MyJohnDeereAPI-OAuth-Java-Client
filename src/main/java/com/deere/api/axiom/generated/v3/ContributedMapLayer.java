package com.deere.api.axiom.generated.v3;

public class ContributedMapLayer extends Resource{
    protected String title;
    protected MapExtent extent;
    protected MapLegend legends;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MapExtent getExtent() {
        return extent;
    }

    public void setExtent(MapExtent extent) {
        this.extent = extent;
    }

    public MapLegend getLegends() {
        return legends;
    }

    public void setLegends(MapLegend legends) {
        this.legends = legends;
    }
}
