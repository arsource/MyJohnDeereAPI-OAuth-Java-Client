package com.deere.api.axiom.generated.v3;

public class MapLegendItem extends Resource{
    protected String label;
    protected Double minimum;
    protected Double maximum;
    protected String hexColor;
    protected Double percent;

    public MapLegendItem(String label, String hexColor, Double percent, Double minimum, Double maximum) {
        this.label = label;
        this.minimum = minimum;
        this.maximum = maximum;
        this.hexColor = hexColor;
        this.percent = percent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
