package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;



public class App extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
    }
}
