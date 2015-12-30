package com.deere.api.axiom.generated.v3;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MachineMeasurement
        extends Resource
        implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected MachineMeasurementDefinition machineMeasurementDefinition;
    protected String networkType;
    protected MachineMeasurement.Series series;

    public MachineMeasurementDefinition getMachineMeasurementDefinition() {
        return machineMeasurementDefinition;
    }

    public void setMachineMeasurementDefinition(MachineMeasurementDefinition value) {
        this.machineMeasurementDefinition = value;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String value) {
        this.networkType = value;
    }

    public MachineMeasurement.Series getSeries() {
        return series;
    }

    public void setSeries(MachineMeasurement.Series value) {
        this.series = value;
    }


    public static class Series implements Serializable
    {

        private final static long serialVersionUID = 1L;
        protected List<MachineMeasurement.Series.Interval> intervals;
        protected String level;
        protected Integer total;

        public List<MachineMeasurement.Series.Interval> getIntervals() {
            if (intervals == null) {
                intervals = new ArrayList<MachineMeasurement.Series.Interval>();
            }
            return this.intervals;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String value) {
            this.level = value;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer value) {
            this.total = value;
        }





        public static class Interval implements Serializable
        {

            private final static long serialVersionUID = 1L;
            protected String intervalStartDate;
            protected String intervalEndDate;
            protected double intervalStartEngineHours;
            protected double intervalEndEngineHours;
            protected MachineMeasurement.Series.Interval.Buckets buckets;

            public String getIntervalStartDate() {
                return intervalStartDate;
            }

            public void setIntervalStartDate(String value) {
                this.intervalStartDate = value;
            }

            public String getIntervalEndDate() {
                return intervalEndDate;
            }

            public void setIntervalEndDate(String value) {
                this.intervalEndDate = value;
            }

            public double getIntervalStartEngineHours() {
                return intervalStartEngineHours;
            }

            public void setIntervalStartEngineHours(double value) {
                this.intervalStartEngineHours = value;
            }

            public double getIntervalEndEngineHours() {
                return intervalEndEngineHours;
            }

            public void setIntervalEndEngineHours(double value) {
                this.intervalEndEngineHours = value;
            }

            public MachineMeasurement.Series.Interval.Buckets getBuckets() {
                return buckets;
            }

            public void setBuckets(MachineMeasurement.Series.Interval.Buckets value) {
                this.buckets = value;
            }



            public static class Buckets implements Serializable
            {

                private final static long serialVersionUID = 1L;
                protected List<MachineMeasurement.Series.Interval.Buckets.Bucket> buckets;
                protected Integer total;

                public List<MachineMeasurement.Series.Interval.Buckets.Bucket> getBuckets() {
                    if (buckets == null) {
                        buckets = new ArrayList<MachineMeasurement.Series.Interval.Buckets.Bucket>();
                    }
                    return this.buckets;
                }

                public Integer getTotal() {
                    return total;
                }

                public void setTotal(Integer value) {
                    this.total = value;
                }




                public static class Bucket implements Serializable
                {

                    private final static long serialVersionUID = 1L;
                    protected Double value;
                    protected String actualStartDate;
                    protected String actualEndDate;
                    protected double midpointStartEngineHours;
                    protected double midpointEndEngineHours;
                    protected Integer sequenceNumber;
                    protected Integer count;
                    public Double getValue() {
                        return value;
                    }
                    public void setValue(Double value) {
                        this.value = value;
                    }
                    public String getActualStartDate() {
                        return actualStartDate;
                    }
                    public void setActualStartDate(String value) {
                        this.actualStartDate = value;
                    }

                    public String getActualEndDate() {
                        return actualEndDate;
                    }
                    public void setActualEndDate(String value) {
                        this.actualEndDate = value;
                    }

                    public double getMidpointStartEngineHours() {
                        return midpointStartEngineHours;
                    }

                    public void setMidpointStartEngineHours(double value) {
                        this.midpointStartEngineHours = value;
                    }

                    public double getMidpointEndEngineHours() {
                        return midpointEndEngineHours;
                    }

                    public void setMidpointEndEngineHours(double value) {
                        this.midpointEndEngineHours = value;
                    }
                    public Integer getSequenceNumber() {
                        return sequenceNumber;
                    }

                    public void setSequenceNumber(Integer value) {
                        this.sequenceNumber = value;
                    }

                    public Integer getCount() {
                        return count;
                    }
                    public void setCount(Integer value) {
                        this.count = value;
                    }

                }

            }

        }

    }

}
