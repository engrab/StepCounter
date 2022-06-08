package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.PowerManager;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.DatabaseRepository_;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.NotificationHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.ServiceHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.SharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;
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
