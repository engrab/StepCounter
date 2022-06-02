package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db.DatabaseRepository;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db.Step;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.BaseNotificationHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.BaseServiceHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.SharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.UserSharedPref;
import org.joda.time.LocalDateTime;


public class BaseStepCountService extends Service implements SensorEventListener {
    public static final int EMIT_NEW_STEPS_INTERVAL_IN_MILLIS = 500;
    SharedPref allPreferences;
    DatabaseRepository databaseRepository;
    private Handler handler;
    private String keyOld;
    BaseNotificationHelper notificationHelper;
    PowerManager powerManager;
    ScreenOnReceiver screenOnReceiver = new ScreenOnReceiver();
    SensorManager sensorManager;
    BaseServiceHelper serviceHelper;
    BaseStepCalculator stepCalculator;
    UserSharedPref userPreferences;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        SensorManager sensorManager = this.sensorManager;
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(19), EMIT_NEW_STEPS_INTERVAL_IN_MILLIS);
        this.handler = new Handler();
        this.keyOld = this.databaseRepository.idForToday();
        periodicStuff();
        ScreenOnReceiver screenOnReceiver = this.screenOnReceiver;
        registerReceiver(screenOnReceiver, screenOnReceiver.getFilter());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void periodicStuff() {
        String idForToday = this.databaseRepository.idForToday();
        if (!idForToday.equals(this.keyOld)) {
            this.keyOld = idForToday;
            this.databaseRepository.insertOrUpdateStep(Step.builder().id(idForToday).steps(0.0f).offset(0.0f).date(LocalDateTime.now()).build());
            new Handler().postDelayed(new Runnable() { // from class: com.kewitschka.stepcounter.services.-$$Lambda$StepCountService$bxWOMZEAoPH1J1JqWwGrKyoo34Q
                @Override // java.lang.Runnable
                public final void run() {
                    BaseStepCountService.this.notifyChanges();
                }
            }, 500L);
            this.stepCalculator.reset();
        } else {
            this.stepCalculator.setOffsetForToday(this.databaseRepository.offsetToday());
            showStepGoalNotification(idForToday);
        }
        this.handler.postDelayed(new Runnable() { // from class: com.kewitschka.stepcounter.services.-$$Lambda$StepCountService$caVwOx1GgrJ9ooFuCSeQNgMaD4E
            @Override // java.lang.Runnable
            public final void run() {
                BaseStepCountService.this.periodicStuff();
            }
        }, 45000L);
    }

    private void showStepGoalNotification(String str) {
        int intValue = this.userPreferences.stepTarget().get().intValue();
        if (this.stepCalculator.getStepsToday() >= intValue && !this.allPreferences.lastStepGoalNotification().get().equals(str)) {
            this.notificationHelper.showStepGoalAchievedNotification(intValue);
            this.allPreferences.lastStepGoalNotification().put(str);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        this.notificationHelper.createServiceNotificationChannel();
        startForeground(BaseNotificationHelper.SERVICE_NOTIFICATION_ID, this.notificationHelper.createServiceNotification());
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.sensorManager.unregisterListener(this);
        this.handler.removeCallbacks(null);
        unregisterReceiver(this.screenOnReceiver);
        this.serviceHelper.startService();
        super.onDestroy();
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values != null) {
            this.stepCalculator.updateStepsSinceBoot(sensorEvent.values[0]);
            if (this.stepCalculator.newStepsToday() > 0.0f && isScreenOn()) {
                notifyChanges();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyChanges() {
        WidgetUpdater.updateWidget(getApplicationContext());
        this.notificationHelper.updateServiceNotification();
    }

    private boolean isScreenOn() {
        return ((PowerManager) getSystemService("power")).isInteractive();
    }
}
