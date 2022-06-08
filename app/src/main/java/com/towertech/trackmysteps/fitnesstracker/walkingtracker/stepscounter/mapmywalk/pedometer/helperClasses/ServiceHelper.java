package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses;

import android.app.ActivityManager;
import android.content.Context;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.SharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses.PermissionService;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class ServiceHelper extends BaseServiceHelper {
    private static ServiceHelper instance_;
    private Context context_;
    private Object rootFragment_;

    private ServiceHelper(Context context) {
        this.context_ = context;
    }

    private ServiceHelper(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
    }

    public static ServiceHelper getInstance_(Context context) {
        if (instance_ == null) {
            OnViewChangedNotifier replaceNotifier = OnViewChangedNotifier.replaceNotifier(null);
            ServiceHelper serviceHelper_ = new ServiceHelper(context.getApplicationContext());
            instance_ = serviceHelper_;
            serviceHelper_.init_();
            OnViewChangedNotifier.replaceNotifier(replaceNotifier);
        }
        return instance_;
    }

    private void init_() {
        this.preferences = new SharedPref(this.context_);
        this.manager = (ActivityManager) this.context_.getSystemService("activity");
        this.permissionService = PermissionService.getInstance_(this.context_, this.rootFragment_);
        this.context = this.context_;
    }
}
