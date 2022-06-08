package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses;

import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;


public class BasePreferencesService {
    UserSharedPref preferences;

    public UserSharedPref getUserPreferences() {
        return this.preferences;
    }
}
