package com.deere.api.axiom.generated.v3;

import java.util.Date;
import java.util.List;

public class FieldOperation extends Resource {

    protected String fieldOperationType;
    protected String cropSeason;
    protected Date startDate;
    protected Date endDate;
    protected String cropName;
    protected String name;
    protected boolean tankMix;
    protected List<MeasurementType> measurementTypes;

    public String getFieldOperationType() {
        return fieldOperationType;
    }

    public void setFieldOperationType(String fieldOperationType) {
        this.fieldOperationType = fieldOperationType;
    }

    public String getCropSeason() {
        return cropSeason;
    }

    public void setCropSeason(String cropSeason) {
        this.cropSeason = cropSeason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTankMix() {
        return tankMix;
    }

    public void setTankMix(boolean tankMix) {
        this.tankMix = tankMix;
    }

    public List<MeasurementType> getMeasurementTypes() {
        return measurementTypes;
    }

    public void setMeasurementTypes(List<MeasurementType> measurementTypes) {
        this.measurementTypes = measurementTypes;
    }
}
