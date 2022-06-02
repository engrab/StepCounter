package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services;

import android.content.Context;
import android.os.Build;


public class BasePermissionService {
    Context context;

    public boolean isPermissionGranted(String str) {
        return Build.VERSION.SDK_INT < 29 || this.context.checkCallingOrSelfPermission(str) == 0;
    }
}
