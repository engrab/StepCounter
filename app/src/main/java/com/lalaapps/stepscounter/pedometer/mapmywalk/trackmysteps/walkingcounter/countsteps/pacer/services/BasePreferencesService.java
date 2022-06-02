package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services;

import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.UserSharedPref;


public class BasePreferencesService {
    UserSharedPref preferences;

    public UserSharedPref getUserPreferences() {
        return this.preferences;
    }
}
