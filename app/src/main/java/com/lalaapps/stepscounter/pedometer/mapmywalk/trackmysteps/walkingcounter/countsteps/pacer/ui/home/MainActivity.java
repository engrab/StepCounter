package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.ui.home;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.ActivityCompat;

import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.SharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.UserSharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.ui.ExitActivity;

import java.util.HashMap;
import java.util.Map;
import org.androidannotations.api.bean.BeanHolder;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class MainActivity extends BaseMainActivity implements BeanHolder, HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private final Map<Class<?>, Object> beans_ = new HashMap();

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier replaceNotifier = OnViewChangedNotifier.replaceNotifier(this.onViewChangedNotifier_);
        init_(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.replaceNotifier(replaceNotifier);
    }

    @Override 
    public <T extends View> T internalFindViewById(int i) {
        return (T) findViewById(i);
    }

    private void init_(Bundle bundle) {
        this.userPreferences = new UserSharedPref(this);
        this.allPreferences = new SharedPref(this);
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void setContentView(int i) {
        super.setContentView(i);
        this.onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        this.onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static IntentBuilder_ intent(Context context) {
        return new IntentBuilder_(context);
    }

    public static IntentBuilder_ intent(Fragment fragment) {
        return new IntentBuilder_(fragment);
    }

    public static IntentBuilder_ intent(androidx.fragment.app.Fragment fragment) {
        return new IntentBuilder_(fragment);
    }

    @Override
    public <T> T getBean(Class<T> cls) {
        return (T) this.beans_.get(cls);
    }

    @Override
    public <T> void putBean(Class<T> cls, T t) {
        this.beans_.put(cls, t);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
//        this.adView = (AdView) hasViews.internalFindViewById(R.id.adView);
//        initViews();
    }

    
    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private androidx.fragment.app.Fragment fragmentSupport_;
        private Fragment fragment_;

        public IntentBuilder_(Context context) {
            super(context, MainActivity.class);
        }

        public IntentBuilder_(Fragment fragment) {
            super(fragment.getActivity(), MainActivity.class);
            this.fragment_ = fragment;
        }

        public IntentBuilder_(androidx.fragment.app.Fragment fragment) {
            super(fragment.getActivity(), MainActivity.class);
            this.fragmentSupport_ = fragment;
        }

        @Override
        public PostActivityStarter startForResult(int i) {
            androidx.fragment.app.Fragment fragment = this.fragmentSupport_;
            if (fragment != null) {
                fragment.startActivityForResult(this.intent, i);
            } else {
                Fragment fragment2 = this.fragment_;
                if (fragment2 != null) {
                    fragment2.startActivityForResult(this.intent, i, this.lastOptions);
                } else if (this.context instanceof Activity) {
                    ActivityCompat.startActivityForResult((Activity) this.context, this.intent, i, this.lastOptions);
                } else {
                    this.context.startActivity(this.intent, this.lastOptions);
                }
            }
            return new PostActivityStarter(this.context);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivity.this, ExitActivity.class));
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
