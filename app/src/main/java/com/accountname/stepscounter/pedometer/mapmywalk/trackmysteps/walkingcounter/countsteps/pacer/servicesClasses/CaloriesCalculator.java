package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses;

import android.content.Context;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.CalcuationHelper;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.UserSharedPref;


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
