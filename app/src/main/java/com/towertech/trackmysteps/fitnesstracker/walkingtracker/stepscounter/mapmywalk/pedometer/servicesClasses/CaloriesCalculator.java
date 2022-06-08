package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses;

import android.content.Context;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.CalcuationHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;


public final class CaloriesCalculator extends BaseCaloriesCalculator {
    private Context context;
    private Object object;

    private CaloriesCalculator(Context context) {
        this.context = context;
        init();
    }

    private CaloriesCalculator(Context context, Object obj) {
        this.context = context;
        this.object = obj;
        init();
    }

    public static CaloriesCalculator getInstance_(Context context) {
        return new CaloriesCalculator(context);
    }

    public static CaloriesCalculator getInstance_(Context context, Object obj) {
        return new CaloriesCalculator(context, obj);
    }

    private void init() {
        this.userPreferences = new UserSharedPref(this.context);
        this.calcuationHelper = CalcuationHelper.getInstance_(this.context, this.object);
    }

    public void rebind(Context context) {
        this.context = context;
        init();
    }
}
