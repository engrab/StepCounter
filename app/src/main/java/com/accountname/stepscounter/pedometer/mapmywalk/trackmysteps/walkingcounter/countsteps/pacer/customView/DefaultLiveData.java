package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.customView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


public class DefaultLiveData<T> extends MutableLiveData<T> {
    private T defaultValue;

    public DefaultLiveData(T t) {
        this.defaultValue = t;
        setValue(t);
    }

    public <B extends LiveData<T>> void bind(LifecycleOwner lifecycleOwner, B b) {
        b.observe(lifecycleOwner, new Observer() {
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DefaultLiveData.this.lambda$bind$0$DefaultLiveData(obj);
            }
        });
    }


    public void lambda$bind$0$DefaultLiveData(Object obj) {

    }

    public void reset() {
        setValue(this.defaultValue);
    }
}
