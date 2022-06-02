package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.PowerManager;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db.DatabaseRepository_;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.NotificationHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.ServiceHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.SharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.UserSharedPref;
import org.androidannotations.api.builder.ServiceIntentBuilder;


public final class StepCountService extends BaseStepCountService {
    public static IntentBuilder_ intent(Context context) {
        return new IntentBuilder_(context);
    }

    private void init_() {
        this.userPreferences = new UserSharedPref(this);
        this.allPreferences = new SharedPref(this);
        this.sensorManager = (SensorManager) getSystemService("sensor");
        this.powerManager = (PowerManager) getSystemService("power");
        this.databaseRepository = DatabaseRepository_.getInstance_(this);
        this.notificationHelper = NotificationHelper.getInstance_(this);
        this.stepCalculator = StepCalculator.getInstance_(this, null);
        this.serviceHelper = ServiceHelper.getInstance_(this);
    }

    @Override
    public void onCreate() {
        init_();
        super.onCreate();
    }

    
    public static class IntentBuilder_ extends ServiceIntentBuilder<IntentBuilder_> {
        public IntentBuilder_(Context context) {
            super(context, StepCountService.class);
        }
    }
}
