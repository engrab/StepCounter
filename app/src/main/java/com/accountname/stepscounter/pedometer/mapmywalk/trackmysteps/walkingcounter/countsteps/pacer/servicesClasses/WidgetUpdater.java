package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.widgetClasses.WidgetProvider;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.widgetClasses.WidgetProvider2;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.widgetClasses.WidgetProvider3;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.widgetClasses.WidgetProvider4;


public class WidgetUpdater {
    public static void updateWidget(Context context) {
        updateWidget(context, WidgetProvider4.class);
        updateWidget(context, WidgetProvider3.class);
        updateWidget(context, WidgetProvider2.class);
    }

    private static void updateWidget(Context context, Class<? extends WidgetProvider> cls) {
        Intent intent = new Intent(context, cls);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, cls)));
        context.sendBroadcast(intent);
    }
}
