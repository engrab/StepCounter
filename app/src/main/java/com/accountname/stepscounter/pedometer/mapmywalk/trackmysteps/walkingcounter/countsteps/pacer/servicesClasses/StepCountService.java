package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.PowerManager;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database.DatabaseRepository_;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.NotificationHelper;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.ServiceHelper;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.SharedPref;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.UserSharedPref;
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
