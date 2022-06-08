package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses;

import android.content.Context;


public final class CalcuationHelper extends BaseCalcuationHelper {
    private Context context_;
    private Object rootFragment_;

    private void init_() {
    }

    private CalcuationHelper(Context context) {
        this.context_ = context;
        init_();
    }

    private CalcuationHelper(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
        init_();
    }

    public static CalcuationHelper getInstance_(Context context) {
        return new CalcuationHelper(context);
    }

    public static CalcuationHelper getInstance_(Context context, Object obj) {
        return new CalcuationHelper(context, obj);
    }

    public void rebind(Context context) {
        this.context_ = context;
        init_();
    }
}
