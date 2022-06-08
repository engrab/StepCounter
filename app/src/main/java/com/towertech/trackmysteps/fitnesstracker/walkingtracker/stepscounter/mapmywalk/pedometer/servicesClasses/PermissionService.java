package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses;

import android.content.Context;


public final class PermissionService extends BasePermissionService {
    private Context context_;
    private Object rootFragment_;

    private PermissionService(Context context) {
        this.context_ = context;
        init_();
    }

    private PermissionService(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
        init_();
    }

    public static PermissionService getInstance_(Context context) {
        return new PermissionService(context);
    }

    public static PermissionService getInstance_(Context context, Object obj) {
        return new PermissionService(context, obj);
    }

    private void init_() {
        this.context = this.context_;
    }

    public void rebind(Context context) {
        this.context_ = context;
        init_();
    }
}
