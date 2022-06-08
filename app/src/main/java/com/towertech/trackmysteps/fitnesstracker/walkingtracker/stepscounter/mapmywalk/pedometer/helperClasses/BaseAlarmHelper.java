package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.receiverClasses.AlarmReceiver;
import org.joda.time.LocalDateTime;


public class BaseAlarmHelper {
    public static final int REQUEST_CODE = 1;
    AlarmManager alarmManager;
    Context context;

    public void scheduleAlarm() {
        Intent intent = new Intent(this.context, AlarmReceiver.class);
        LocalDateTime plusMinutes = LocalDateTime.now().plusMinutes(5);
        PendingIntent broadcast;
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.R) {
             broadcast = PendingIntent.getBroadcast(this.context, 1, intent,  PendingIntent.FLAG_IMMUTABLE);

        }else {

            broadcast = PendingIntent.getBroadcast(this.context, 1, intent, PendingIntent.FLAG_ONE_SHOT);

        }
        if (Build.VERSION.SDK_INT >= 23) {
            this.alarmManager.setExactAndAllowWhileIdle(1, plusMinutes.toDate().getTime(), broadcast);
        } else {
            this.alarmManager.setExact(1, plusMinutes.toDate().getTime(), broadcast);
        }
    }
}
