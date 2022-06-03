package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.activities.mainActivities;

import android.content.Context;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database.DatabaseRepository_;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.UserSharedPref;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses.StepCalculator;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class ViewModel extends BaseViewModel {
    private static ViewModel instance_;
    private Context context_;
    private Object rootFragment_;

    public Context getContext_() {
        return this.context_;
    }

    public Object getRootFragment_() {
        return this.rootFragment_;
    }

    private ViewModel(Context context) {
        this.context_ = context;
    }

    private ViewModel(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
    }

    public static ViewModel getInstance_(Context context) {
        if (instance_ == null) {
            OnViewChangedNotifier replaceNotifier = OnViewChangedNotifier.replaceNotifier(null);
            ViewModel mainViewModel_ = new ViewModel(context.getApplicationContext());
            instance_ = mainViewModel_;
            mainViewModel_.init_();
            OnViewChangedNotifier.replaceNotifier(replaceNotifier);
        }
        return instance_;
    }

    private void init_() {
        this.preferences = new UserSharedPref(this.context_);
        this.databaseRepository = DatabaseRepository_.getInstance_(this.context_);
        this.stepCalculator = StepCalculator.getInstance_(this.context_, this.rootFragment_);
    }
}
