package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database;

import java.util.Date;
import org.joda.time.LocalDateTime;


public class DatabaseConverter {
    public static LocalDateTime fromTimestamp(Long l) {
        if (l == null) {
            return null;
        }
        return LocalDateTime.fromDateFields(new Date(l.longValue()));
    }

    public static Long dateToTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Long.valueOf(localDateTime.toDate().getTime());
    }
}
