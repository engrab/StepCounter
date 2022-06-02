package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.AlarmHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.ServiceHelper;


public class AlarmReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ServiceHelper.getInstance_(context).startService();
        AlarmHelper.getInstance_(context).scheduleAlarm();
    }
}
