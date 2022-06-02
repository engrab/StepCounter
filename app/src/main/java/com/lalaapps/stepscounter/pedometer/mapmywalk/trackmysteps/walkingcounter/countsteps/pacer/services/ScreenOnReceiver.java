package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.NotificationHelper;


public class ScreenOnReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
            NotificationHelper.getInstance_(context).updateServiceNotification();
            WidgetUpdater.updateWidget(context);
        }
    }

    public IntentFilter getFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        return intentFilter;
    }
}
