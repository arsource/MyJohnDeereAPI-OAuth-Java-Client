package com.deere.api.axiom.generated.v3;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reading extends Resource{
    private String unit;
    private double valueAsDouble;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValueAsDouble() {
        return valueAsDouble;
    }

    public void setValueAsDouble(double valueAsDouble) {
        this.valueAsDouble = valueAsDouble;
    }
}
