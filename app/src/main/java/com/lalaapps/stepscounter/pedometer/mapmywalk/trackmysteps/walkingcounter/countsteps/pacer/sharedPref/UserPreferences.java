package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref;


public interface UserPreferences {
    boolean configured();

    int sizeInCm();

    float stepSizeInM();

    int stepTarget();

    int weightInKg();

    boolean woman();

    int yearOfBirth();
}
