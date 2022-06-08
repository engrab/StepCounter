package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences;


public interface UserPreferences {
    boolean configured();

    int sizeInCm();

    float stepSizeInM();

    int stepTarget();

    int weightInKg();

    boolean woman();

    int yearOfBirth();
}
