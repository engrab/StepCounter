package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db;


public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract StepDatabaseAccessor accessor();
}
