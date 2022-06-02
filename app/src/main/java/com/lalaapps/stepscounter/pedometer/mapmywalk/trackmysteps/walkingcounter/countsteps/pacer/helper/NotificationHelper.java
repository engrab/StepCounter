package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper;

import android.app.NotificationManager;
import android.content.Context;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db.DatabaseRepository_;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class NotificationHelper extends BaseNotificationHelper {
    private static NotificationHelper instance_;
    private Context context_;
    private Object rootFragment_;

    private NotificationHelper(Context context) {
        this.context_ = context;
    }

    private NotificationHelper(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
    }

    public static NotificationHelper getInstance_(Context context) {
        if (instance_ == null) {
            OnViewChangedNotifier replaceNotifier = OnViewChangedNotifier.replaceNotifier(null);
            NotificationHelper notificationHelper_ = new NotificationHelper(context.getApplicationContext());
            instance_ = notificationHelper_;
            notificationHelper_.init_();
            OnViewChangedNotifier.replaceNotifier(replaceNotifier);
        }
        return instance_;
    }

    private void init_() {
        this.notificationManager = (NotificationManager) this.context_.getSystemService("notification");
        this.formatHelper = FormatHelper.getInstance_(this.context_, this.rootFragment_);
        this.databaseRepository = DatabaseRepository_.getInstance_(this.context_);
        this.context = this.context_;
    }
}
