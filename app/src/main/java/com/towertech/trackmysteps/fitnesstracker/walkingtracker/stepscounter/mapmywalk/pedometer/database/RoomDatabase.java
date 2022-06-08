package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database;


public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract StepDatabaseAccessor accessor();
}
