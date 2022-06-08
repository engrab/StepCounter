package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.statisticsActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSpinner;
import com.github.mikephil.charting.charts.BarChart;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.R;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.DatabaseRepository_;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.FormatHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.SharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.utilsClasses.DateAndTimeUtils_;
import java.util.HashMap;
import java.util.Map;
import org.androidannotations.api.bean.BeanHolder;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class StatsFragment extends BaseStatsFragment implements BeanHolder, HasViews, OnViewChangedListener {
    private View contentView_;
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
        View view = this.contentView_;
        if (view == null) {
            return null;
        }
        return (T) view.findViewById(i);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.contentView_ = onCreateView;
        return onCreateView;
    }

    @Override 
    public void onDestroyView() {
        super.onDestroyView();
        this.contentView_ = null;
        this.chart = null;
        this.daysSpinner = null;
        this.averageSteps = null;
        this.averageKm = null;
        this.averageMin = null;
        this.averageCalories = null;
        this.targetViewContainer = null;
    }

    private void init_(Bundle bundle) {
        this.userPreferences = new UserSharedPref(getActivity());
        this.allPreferences = new SharedPref(getActivity());
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        this.databaseRepository = DatabaseRepository_.getInstance_(getActivity());
        this.dateAndTimeUtils = DateAndTimeUtils_.getInstance_(getActivity());
        this.formatHelper = FormatHelper.getInstance_(getActivity(), this);
    }

    @Override 
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static FragmentBuilder_ builder() {
        return new FragmentBuilder_();
    }

    @Override // org.androidannotations.api.bean.BeanHolder
    public <T> T getBean(Class<T> cls) {
        return (T) this.beans_.get(cls);
    }

    @Override // org.androidannotations.api.bean.BeanHolder
    public <T> void putBean(Class<T> cls, T t) {
        this.beans_.put(cls, t);
    }

    @Override // org.androidannotations.api.view.OnViewChangedListener
    public void onViewChanged(HasViews hasViews) {
        this.chart = (BarChart) hasViews.internalFindViewById(R.id.chart);
        this.daysSpinner = (AppCompatSpinner) hasViews.internalFindViewById(R.id.daysSpinner);
        this.averageSteps = (TextView) hasViews.internalFindViewById(R.id.averageSteps);
        this.averageKm = (TextView) hasViews.internalFindViewById(R.id.averageKm);
        this.averageMin = (TextView) hasViews.internalFindViewById(R.id.averageMin);
        this.averageCalories = (TextView) hasViews.internalFindViewById(R.id.averageCalories);
        this.targetViewContainer = (LinearLayout) hasViews.internalFindViewById(R.id.targetViewContainer);
        initViews();
    }

    
    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, BaseStatsFragment> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.androidannotations.api.builder.FragmentBuilder
        public BaseStatsFragment build() {
            StatsFragment statsFragment_ = new StatsFragment();
            statsFragment_.setArguments(this.args);
            return statsFragment_;
        }
    }
}
