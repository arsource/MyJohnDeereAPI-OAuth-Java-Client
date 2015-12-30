package com.deere.api.axiom.generated.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MachineMeasurementDefinition
        extends Resource
        implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected String name;
    protected MachineMeasurementDefinition.BucketDefinitions bucketDefinitions;
    protected MachineMeasurementDefinition.AxesGroup axesGroup;
    protected MachineMeasurementDefinition.AggregationType aggregationType;
    protected String unitOfMeasure;
    protected MachineMeasurementDefinition.MeasurementType measurementType;
    protected MachineMeasurementDefinition.RepresentationType representationType;

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the bucketDefinitions property.
     *
     * @return
     *     possible object is
     *     {@link MachineMeasurementDefinition.BucketDefinitions }
     *
     */
    public MachineMeasurementDefinition.BucketDefinitions getBucketDefinitions() {
        return bucketDefinitions;
    }

    /**
     * Sets the value of the bucketDefinitions property.
     *
     * @param value
     *     allowed object is
     *     {@link MachineMeasurementDefinition.BucketDefinitions }
     *
     */
    public void setBucketDefinitions(MachineMeasurementDefinition.BucketDefinitions value) {
        this.bucketDefinitions = value;
    }

    /**
     * Gets the value of the axesGroup property.
     *
     * @return
     *     possible object is
     *     {@link MachineMeasurementDefinition.AxesGroup }
     *
     */
    public MachineMeasurementDefinition.AxesGroup getAxesGroup() {
        return axesGroup;
    }

    /**
     * Sets the value of the axesGroup property.
     *
     * @param value
     *     allowed object is
     *     {@link MachineMeasurementDefinition.AxesGroup }
     *
     */
    public void setAxesGroup(MachineMeasurementDefinition.AxesGroup value) {
        this.axesGroup = value;
    }

    /**
     * Gets the value of the aggregationType property.
     *
     * @return
     *     possible object is
     *     {@link MachineMeasurementDefinition.AggregationType }
     *
     */
    public MachineMeasurementDefinition.AggregationType getAggregationType() {
        return aggregationType;
    }

    /**
     * Sets the value of the aggregationType property.
     *
     * @param value
     *     allowed object is
     *     {@link MachineMeasurementDefinition.AggregationType }
     *
     */
    public void setAggregationType(MachineMeasurementDefinition.AggregationType value) {
        this.aggregationType = value;
    }

    /**
     * Gets the value of the unitOfMeasure property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets the value of the unitOfMeasure property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUnitOfMeasure(String value) {
        this.unitOfMeasure = value;
    }

    /**
     * Gets the value of the measurementType property.
     *
     * @return
     *     possible object is
     *     {@link MachineMeasurementDefinition.MeasurementType }
     *
     */
    public MachineMeasurementDefinition.MeasurementType getMeasurementType() {
        return measurementType;
    }

    /**
     * Sets the value of the measurementType property.
     *
     * @param value
     *     allowed object is
     *     {@link MachineMeasurementDefinition.MeasurementType }
     *
     */
    public void setMeasurementType(MachineMeasurementDefinition.MeasurementType value) {
        this.measurementType = value;
    }

    /**
     * Gets the value of the representationType property.
     *
     * @return
     *     possible object is
     *     {@link MachineMeasurementDefinition.RepresentationType }
     *
     */
    public MachineMeasurementDefinition.RepresentationType getRepresentationType() {
        return representationType;
    }

    /**
     * Sets the value of the representationType property.
     *
     * @param value
     *     allowed object is
     *     {@link MachineMeasurementDefinition.RepresentationType }
     *
     */
    public void setRepresentationType(MachineMeasurementDefinition.RepresentationType value) {
        this.representationType = value;
    }


    public static class AggregationType implements Serializable
    {

        private final static long serialVersionUID = 1L;
        protected String value;
        protected String id;

        /**
         * Gets the value of the value property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the id property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setId(String value) {
            this.id = value;
        }



    }


    public static class AxesGroup implements Serializable
    {

        private final static long serialVersionUID = 1L;
        protected String value;
        protected Integer priority;
        protected String id;

        /**
         * Gets the value of the value property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the priority property.
         *
         * @return
         *     possible object is
         *     {@link Integer }
         *
         */
        public Integer getPriority() {
            return priority;
        }

        /**
         * Sets the value of the priority property.
         *
         * @param value
         *     allowed object is
         *     {@link Integer }
         *
         */
        public void setPriority(Integer value) {
            this.priority = value;
        }

        /**
         * Gets the value of the id property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setId(String value) {
            this.id = value;
        }



    }


    public static class BucketDefinitions
            implements Serializable
    {

        private final static long serialVersionUID = 1L;
        protected List<MachineMeasurementDefinition.BucketDefinitions.BucketDefinition> bucketDefinitions;

        public List<BucketDefinition> getBucketDefinitions() {
            if (bucketDefinitions == null) {
                bucketDefinitions = new ArrayList<BucketDefinition>();
            }
            return this.bucketDefinitions;
        }



        public static class BucketDefinition
                extends Resource
                implements Serializable
        {

            private final static long serialVersionUID = 1L;
            protected String description;
            protected Double minimumValue;
            protected Double maximumValue;
            protected Boolean rangeIndicator;
            protected Integer sequenceNumber;

            public String getDescription() {
                return description;
            }

            public void setDescription(String value) {
                this.description = value;
            }

            public Double getMinimumValue() {
                return minimumValue;
            }

            public void setMinimumValue(Double value) {
                this.minimumValue = value;
            }

            public Double getMaximumValue() {
                return maximumValue;
            }

            public void setMaximumValue(Double value) {
                this.maximumValue = value;
            }

            public Boolean isRangeIndicator() {
                return rangeIndicator;
            }

            public void setRangeIndicator(Boolean value) {
                this.rangeIndicator = value;
            }

            public Integer getSequenceNumber() {
                return sequenceNumber;
            }

            public void setSequenceNumber(Integer value) {
                this.sequenceNumber = value;
            }


        }

    }


    public static class MeasurementType implements Serializable
    {

        private final static long serialVersionUID = 1L;
        protected String value;
        protected String id;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String value) {
            this.id = value;
        }

    }


    public static class RepresentationType implements Serializable
    {

        private final static long serialVersionUID = 1L;
        protected String value;
        protected String id;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String value) {
            this.id = value;
        }


    }

}


