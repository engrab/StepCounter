package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer;

import android.app.Application;

import androidx.multidex.MultiDex;



public class App extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
    }
}
