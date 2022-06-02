package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services;

import android.content.Context;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.UserSharedPref;


public final class PreferencesService extends BasePreferencesService {
    private Context context_;
    private Object rootFragment_;

    private PreferencesService(Context context) {
        this.context_ = context;
        init_();
    }

    private PreferencesService(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
        init_();
    }

    public static PreferencesService getInstance_(Context context) {
        return new PreferencesService(context);
    }

    public static PreferencesService getInstance_(Context context, Object obj) {
        return new PreferencesService(context, obj);
    }

    private void init_() {
        this.preferences = new UserSharedPref(this.context_);
    }

    public void rebind(Context context) {
        this.context_ = context;
        init_();
    }
}
