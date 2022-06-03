package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.receiverClasses;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.AlarmHelper;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.ServiceHelper;


public class BootAndUpdateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") || intent.getAction().equals("android.intent.action.MY_PACKAGE_REPLACED")) {
            ServiceHelper.getInstance_(context).startService();
            AlarmHelper.getInstance_(context).scheduleAlarm();
        }
    }
}
