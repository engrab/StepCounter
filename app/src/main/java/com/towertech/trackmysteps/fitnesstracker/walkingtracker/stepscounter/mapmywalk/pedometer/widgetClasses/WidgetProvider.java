package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.widgetClasses;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.R;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.DatabaseRepository_;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.Step;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.FormatHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.mainActivities.MainActivity;


public class WidgetProvider extends AppWidgetProvider {
    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        Class<?> cls = getClass();
        if (cls == WidgetProvider2.class) {
            update(context, appWidgetManager, iArr, R.layout.widget_2x1, true, false, false, false);
        } else if (cls == WidgetProvider4.class) {
            update(context, appWidgetManager, iArr, R.layout.widget_4x1, true, true, true, true);
        } else if (cls == WidgetProvider3.class) {
            update(context, appWidgetManager, iArr, R.layout.widget_3x1, true, true, false, false);
        }
        super.onUpdate(context, appWidgetManager, iArr);
    }

    public void update(Context context, AppWidgetManager appWidgetManager, int[] iArr, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        DatabaseRepository_ instance_ = DatabaseRepository_.getInstance_(context);
        FormatHelper instance_2 = FormatHelper.getInstance_(context);
        Step stepToday = instance_.stepToday();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), i);
        if (z) {
            remoteViews.setTextViewText(R.id.steps, instance_2.formatSteps(stepToday.getSteps()));
        }
        if (z2) {
            remoteViews.setTextViewText(R.id.km, instance_2.formatKm(stepToday.getSteps()));
        }
        if (z3) {
            remoteViews.setTextViewText(R.id.min, instance_2.formatMinutes(stepToday.getSteps()));
        }
        if (z4) {
            remoteViews.setTextViewText(R.id.calories, instance_2.formatCalories(stepToday.getSteps()));
        }
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.R) {
            remoteViews.setOnClickPendingIntent(R.id.rootLayout, PendingIntent.getActivity(context, 7777, MainActivity.intent(context).get(), PendingIntent.FLAG_IMMUTABLE));

        }else {
            remoteViews.setOnClickPendingIntent(R.id.rootLayout, PendingIntent.getActivity(context, 7777, MainActivity.intent(context).get(), PendingIntent.FLAG_ONE_SHOT));

        }
        for (int i2 : iArr) {
            appWidgetManager.updateAppWidget(i2, remoteViews);
        }
    }
}
