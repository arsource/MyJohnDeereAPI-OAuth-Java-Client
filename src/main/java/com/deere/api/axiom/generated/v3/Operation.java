package com.deere.api.axiom.generated.v3;


import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/*
******Operation Type*****
MowingOperation
SeedingOperation
ConditioningOperation
BalingOperation
ScoutingOperation
SoilSamplingOperation
MiscellaneousOperation
ApplicationOperation
TillageOperation
HarvestOperation
TransportOperation
*/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type", include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TillageOperation.class, name = "tillageOperation"),
        @JsonSubTypes.Type(value = SeedingOperation.class, name = "seedingOperation"),
        @JsonSubTypes.Type(value = ApplicationOperation.class, name = "applicationOperation")
})
public abstract class Operation extends Resource {

    protected String notes;
    protected String type;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
