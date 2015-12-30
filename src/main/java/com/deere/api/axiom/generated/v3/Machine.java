package com.deere.api.axiom.generated.v3;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class Machine extends Resource {
    private final static long serialVersionUID = 1L;
    protected String vin;
    protected String name;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
