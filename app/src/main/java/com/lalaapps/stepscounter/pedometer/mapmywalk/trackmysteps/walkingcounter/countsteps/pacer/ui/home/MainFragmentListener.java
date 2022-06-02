package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.ui.home;

import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;

public final  class MainFragmentListener implements OnCompleteListener {
    public static final  MainFragmentListener INSTANCE = new MainFragmentListener();

    private  MainFragmentListener() {
    }

    @Override
    public final void onComplete(Task task) {
        BaseHomeFragment.lambda$null$0(task);
    }
}
