package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database;


public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract StepDatabaseAccessor accessor();
}
