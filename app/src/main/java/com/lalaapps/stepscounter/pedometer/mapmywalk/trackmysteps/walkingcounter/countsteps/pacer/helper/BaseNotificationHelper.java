package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.R;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db.DatabaseRepository;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db.Step;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.ui.home.MainActivity;


public class BaseNotificationHelper {
    public static final int SERVICE_NOTIFICATION_ID = 5555;
    public static final int STEP_GOAL_NOTIFICATION_ID = 6666;
    Context context;
    DatabaseRepository databaseRepository;
    BaseFormatHelper formatHelper;
    NotificationManager notificationManager;
    private String SERVICE_CHANNEL_ID = "1";
    private String INFO_CHANNEL_ID = ExifInterface.GPS_MEASUREMENT_2D;

    public Notification createServiceNotification() {
        Step stepToday = this.databaseRepository.stepToday();
        Context context = this.context;
        PendingIntent activity;
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.R) {

            activity = PendingIntent.getActivity(context, 8888, MainActivity.intent(context).get(), PendingIntent.FLAG_IMMUTABLE);

        }else {
            activity = PendingIntent.getActivity(context, 8888, MainActivity.intent(context).get(), PendingIntent.FLAG_ONE_SHOT);


        }
        RemoteViews remoteViews = new RemoteViews(this.context.getPackageName(), (int) R.layout.notification);
        remoteViews.setTextViewText(R.id.steps, this.formatHelper.formatSteps(stepToday.getSteps()));
        remoteViews.setTextViewText(R.id.km, this.formatHelper.formatKm(stepToday.getSteps()));
        remoteViews.setTextViewText(R.id.min, this.formatHelper.formatMinutes(stepToday.getSteps()));
        remoteViews.setTextViewText(R.id.calories, this.formatHelper.formatCalories(stepToday.getSteps()));
        return new NotificationCompat.Builder(this.context, this.SERVICE_CHANNEL_ID).setSmallIcon(R.drawable.ic_notification).setContentTitle(this.context.getString(R.string.app_name)).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setCustomContentView(remoteViews).setPriority(2).setContentText(this.context.getString(R.string.service_channel_description)).setVibrate(null).setSound(null).setOnlyAlertOnce(true).setContentIntent(activity).build();
    }

    public void showStepGoalAchievedNotification(int i) {
        createInfoNotificationChannel();
        this.notificationManager.notify(STEP_GOAL_NOTIFICATION_ID, createStepGoalNotification(i));
    }

    private Notification createStepGoalNotification(int i) {
        Context context = this.context;


        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.R) {
            return new NotificationCompat.Builder(this.context, this.SERVICE_CHANNEL_ID).setSmallIcon(R.drawable.ic_notification).setContentTitle(this.context.getString(R.string.goal_achieved_title)).setStyle(new NotificationCompat.BigTextStyle().bigText(this.context.getString(R.string.goal_achieved_description, Integer.valueOf(i)))).setPriority(0).setContentIntent(PendingIntent.getActivity(context, 9999, MainActivity.intent(context).get(), PendingIntent.FLAG_IMMUTABLE)).build();

        }else {
            return new NotificationCompat.Builder(this.context, this.SERVICE_CHANNEL_ID).setSmallIcon(R.drawable.ic_notification).setContentTitle(this.context.getString(R.string.goal_achieved_title)).setStyle(new NotificationCompat.BigTextStyle().bigText(this.context.getString(R.string.goal_achieved_description, Integer.valueOf(i)))).setPriority(0).setContentIntent(PendingIntent.getActivity(context, 9999, MainActivity.intent(context).get(), PendingIntent.FLAG_ONE_SHOT)).build();


        }
    }

    public void updateServiceNotification() {
        this.notificationManager.notify(SERVICE_NOTIFICATION_ID, createServiceNotification());
    }

    public void createServiceNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            String string = this.context.getString(R.string.service_channel_description);
            String string2 = this.context.getString(R.string.service_channel_description);
            NotificationChannel notificationChannel = new NotificationChannel(this.SERVICE_CHANNEL_ID, string, 4);
            notificationChannel.setDescription(string2);
            notificationChannel.enableLights(false);
            notificationChannel.setLightColor(-16711936);
            notificationChannel.setShowBadge(false);
            notificationChannel.setVibrationPattern(null);
            notificationChannel.setSound(null, null);
            this.notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void createInfoNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            String string = this.context.getString(R.string.info_channel_description);
            String string2 = this.context.getString(R.string.info_channel_description);
            NotificationChannel notificationChannel = new NotificationChannel(this.INFO_CHANNEL_ID, string, 3);
            notificationChannel.setDescription(string2);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-16711936);
            this.notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
