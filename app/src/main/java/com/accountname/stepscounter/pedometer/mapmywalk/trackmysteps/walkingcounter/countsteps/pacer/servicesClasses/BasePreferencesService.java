package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses;

import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.UserSharedPref;


public class BasePreferencesService {
    UserSharedPref preferences;

    public UserSharedPref getUserPreferences() {
        return this.preferences;
    }
}
