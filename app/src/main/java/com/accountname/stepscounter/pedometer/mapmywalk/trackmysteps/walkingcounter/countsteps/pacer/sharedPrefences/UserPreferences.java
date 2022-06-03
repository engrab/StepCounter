package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences;


public interface UserPreferences {
    boolean configured();

    int sizeInCm();

    float stepSizeInM();

    int stepTarget();

    int weightInKg();

    boolean woman();

    int yearOfBirth();
}
