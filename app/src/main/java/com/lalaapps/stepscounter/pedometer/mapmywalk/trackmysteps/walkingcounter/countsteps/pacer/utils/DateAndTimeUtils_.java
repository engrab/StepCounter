package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.utils;

import android.content.Context;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class DateAndTimeUtils_ extends DateAndTimeUtils {
    private static DateAndTimeUtils_ instance_;
    private Context context_;
    private Object rootFragment_;

    private DateAndTimeUtils_(Context context) {
        this.context_ = context;
    }

    private DateAndTimeUtils_(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
    }

    public static DateAndTimeUtils_ getInstance_(Context context) {
        if (instance_ == null) {
            OnViewChangedNotifier replaceNotifier = OnViewChangedNotifier.replaceNotifier(null);
            DateAndTimeUtils_ dateAndTimeUtils_ = new DateAndTimeUtils_(context.getApplicationContext());
            instance_ = dateAndTimeUtils_;
            dateAndTimeUtils_.init_();
            OnViewChangedNotifier.replaceNotifier(replaceNotifier);
        }
        return instance_;
    }

    private void init_() {
        this.context = this.context_;
    }
}
