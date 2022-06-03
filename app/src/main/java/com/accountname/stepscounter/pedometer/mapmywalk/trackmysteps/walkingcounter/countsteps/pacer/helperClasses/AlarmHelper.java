package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses;

import android.app.AlarmManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class AlarmHelper extends BaseAlarmHelper {
    private static AlarmHelper instance_;
    private Context context_;
    private Object rootFragment_;

    private AlarmHelper(Context context) {
        this.context_ = context;
    }

    private AlarmHelper(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
    }

    public static AlarmHelper getInstance_(Context context) {
        if (instance_ == null) {
            OnViewChangedNotifier replaceNotifier = OnViewChangedNotifier.replaceNotifier(null);
            AlarmHelper alarmHelper_ = new AlarmHelper(context.getApplicationContext());
            instance_ = alarmHelper_;
            alarmHelper_.init_();
            OnViewChangedNotifier.replaceNotifier(replaceNotifier);
        }
        return instance_;
    }

    private void init_() {
        this.alarmManager = (AlarmManager) this.context_.getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.context = this.context_;
    }
}
