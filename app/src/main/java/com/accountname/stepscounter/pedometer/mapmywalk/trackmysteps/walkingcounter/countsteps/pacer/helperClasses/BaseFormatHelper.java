package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses;

import android.content.Context;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.R;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.UserSharedPref;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses.BaseCaloriesCalculator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.LocalDateTime;


public class BaseFormatHelper {
    BaseCalcuationHelper calcuationHelper;
    BaseCaloriesCalculator caloriesCalculator;
    Context context;
    UserSharedPref userPreferences;
    private final Map<Integer, String> weekdayMapping = new HashMap();

    public void init() {
        initWeekdayMappings();
    }

    public String formatSteps(float f) {
        return String.format(Locale.getDefault(), "%.0f", Float.valueOf(f));
    }

    public String formatKm(float f) {
        return String.format(Locale.getDefault(), "%.2f", Float.valueOf((f * this.userPreferences.stepSizeInM().get().floatValue()) / 1000.0f));
    }

    public String formatMinutes(float f) {
        return String.format(Locale.getDefault(), "%.0f", Float.valueOf(this.calcuationHelper.stepsToActiveInMinute(f)));
    }

    public String formatCalories(float f) {
        return String.format(Locale.getDefault(), "%.0f", Float.valueOf(this.caloriesCalculator.caloriesFromSteps(f)));
    }

    public String dateToWeekday(LocalDateTime localDateTime) {
        return this.weekdayMapping.get(Integer.valueOf(localDateTime.getDayOfWeek()));
    }

    private void initWeekdayMappings() {
        this.weekdayMapping.put(1, this.context.getResources().getString(R.string.monday));
        this.weekdayMapping.put(2, this.context.getResources().getString(R.string.tuesday));
        this.weekdayMapping.put(3, this.context.getResources().getString(R.string.wednesday));
        this.weekdayMapping.put(4, this.context.getResources().getString(R.string.thursday));
        this.weekdayMapping.put(5, this.context.getResources().getString(R.string.friday));
        this.weekdayMapping.put(6, this.context.getResources().getString(R.string.saturday));
        this.weekdayMapping.put(7, this.context.getResources().getString(R.string.sunday));
    }
}
