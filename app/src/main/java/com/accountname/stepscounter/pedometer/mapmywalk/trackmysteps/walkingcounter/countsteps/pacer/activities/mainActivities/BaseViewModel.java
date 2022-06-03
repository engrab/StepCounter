package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.activities.mainActivities;

import androidx.lifecycle.ViewModel;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.customView.DefaultLiveData;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database.DatabaseRepository;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.UserSharedPref;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses.BaseStepCalculator;
import java.util.Locale;


public class BaseViewModel extends ViewModel {
    public static final int WAVE_AMPLITUDE_ANIM_INTERVAL = 200;
    public static final int WAVE_AMPLITUDE_ANIM_MAX = 30;
    public static final int WAVE_AMPLITUDE_ANIM_MIN = 10;
    public static final int WAVE_AMPLITUDE_ANIM_STEP = 5;
    DatabaseRepository databaseRepository;
    UserSharedPref preferences;
    BaseStepCalculator stepCalculator;
    DefaultLiveData<Boolean> waveAmplitudeAnimDirectionUp = new DefaultLiveData<>(true);

    public DatabaseRepository getDatabaseRepository() {
        return this.databaseRepository;
    }

    public BaseStepCalculator getStepCalculator() {
        return this.stepCalculator;
    }

    public UserSharedPref getPreferences() {
        return this.preferences;
    }

    public DefaultLiveData<Boolean> getWaveAmplitudeAnimDirectionUp() {
        return this.waveAmplitudeAnimDirectionUp;
    }

    public String formattedSteps(float f) {
        return String.format(Locale.getDefault(), "%.0f", Float.valueOf(f));
    }
}
