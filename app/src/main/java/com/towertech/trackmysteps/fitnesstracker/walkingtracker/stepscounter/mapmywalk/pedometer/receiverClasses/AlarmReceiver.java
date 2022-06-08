package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.receiverClasses;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.AlarmHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.ServiceHelper;


public class AlarmReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ServiceHelper.getInstance_(context).startService();
        AlarmHelper.getInstance_(context).scheduleAlarm();
    }
}
