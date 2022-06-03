package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.utilsClasses;

import android.content.Context;
import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateAndTimeUtils {
    Context context;

    public String timestampToDate(Date date) {
        Locale currentLocale = getCurrentLocale();
        return new SimpleDateFormat(((SimpleDateFormat) SimpleDateFormat.getDateInstance(3, currentLocale)).toPattern().replaceAll("\\W?[Yy]+\\W?", ""), currentLocale).format(date);
    }

    private Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.context.getResources().getConfiguration().getLocales().get(0);
        }
        return this.context.getResources().getConfiguration().locale;
    }
}
