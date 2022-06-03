package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses;

import android.content.Context;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.UserSharedPref;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses.CaloriesCalculator;


public final class FormatHelper extends BaseFormatHelper {
    private Context context_;
    private Object rootFragment_;

    private FormatHelper(Context context) {
        this.context_ = context;
        init_();
    }

    private FormatHelper(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
        init_();
    }

    public static FormatHelper getInstance_(Context context) {
        return new FormatHelper(context);
    }

    public static FormatHelper getInstance_(Context context, Object obj) {
        return new FormatHelper(context, obj);
    }

    private void init_() {
        this.userPreferences = new UserSharedPref(this.context_);
        this.calcuationHelper = CalcuationHelper.getInstance_(this.context_, this.rootFragment_);
        this.caloriesCalculator = CaloriesCalculator.getInstance_(this.context_, this.rootFragment_);
        this.context = this.context_;
        init();
    }

    public void rebind(Context context) {
        this.context_ = context;
        init_();
    }
}
