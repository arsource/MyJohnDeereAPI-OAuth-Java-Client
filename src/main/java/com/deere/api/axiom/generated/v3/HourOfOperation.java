package com.deere.api.axiom.generated.v3;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HourOfOperation extends Resource {

    private long startDate;
    private long endDate;
    private int engineState;
}
