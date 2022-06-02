package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.SharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services.BasePermissionService;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services.StepCountService;


public class BaseServiceHelper {
    Context context;
    ActivityManager manager;
    BasePermissionService permissionService;
    SharedPref preferences;

    public void startService() {
        if (!this.preferences.servicePaused().get().booleanValue() && this.permissionService.isPermissionGranted("android.permission.ACTIVITY_RECOGNITION") && !isMyServiceRunning(StepCountService.class)) {
            ContextCompat.startForegroundService(this.context, new Intent(this.context, StepCountService.class));
        }
    }

    public void stopService() {
        this.context.stopService(new Intent(this.context, StepCountService.class));
    }

    private boolean isMyServiceRunning(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : this.manager.getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
