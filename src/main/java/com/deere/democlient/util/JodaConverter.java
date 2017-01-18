package com.deere.democlient.util;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;

public class JodaConverter {
    private static final Chronology UTC_CHRONOLOGY;

    public JodaConverter() {
    }

    public static DateTime unmarshal(String date) {
        return date == null?null:new DateTime(date, UTC_CHRONOLOGY);
    }

    public static String marshal(DateTime dateTime) {
        return dateTime == null?null:dateTime.withChronology(UTC_CHRONOLOGY).toString();
    }

    static {
        UTC_CHRONOLOGY = ISOChronology.getInstance(DateTimeZone.UTC);
    }
}
