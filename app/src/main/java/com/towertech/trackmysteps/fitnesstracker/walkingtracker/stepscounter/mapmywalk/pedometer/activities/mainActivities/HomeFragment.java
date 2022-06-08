package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.mainActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.R;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.adsClasses.Unity;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.DatabaseRepository_;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.AlarmHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.CalcuationHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.FormatHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.ServiceHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.SharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses.CaloriesCalculator;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses.PermissionService;
import com.unity3d.mediation.IInterstitialAdShowListener;
import com.unity3d.mediation.errors.ShowError;

import java.util.HashMap;
import java.util.Map;

import org.androidannotations.api.bean.BeanHolder;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class HomeFragment extends BaseHomeFragment implements BeanHolder, HasViews, OnViewChangedListener {
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
        return view.findViewById(i);
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
        this.donutView = null;
        this.playPauseButton = null;
        this.km = null;
        this.calories = null;
        this.activeInMin = null;
        this.steps = null;
        this.target = null;
        this.user = null;
    }

    private void init_(Bundle bundle) {
        this.userPreferences = new UserSharedPref(getActivity());
        this.allPreferences = new SharedPref(getActivity());
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        this.viewModel = ViewModel.getInstance_(getActivity());
        this.alarmHelper = AlarmHelper.getInstance_(getActivity());
        this.permissionService = PermissionService.getInstance_(getActivity(), this);
        this.databaseRepository = DatabaseRepository_.getInstance_(getActivity());
        this.serviceHelper = ServiceHelper.getInstance_(getActivity());
        this.calcuationHelper = CalcuationHelper.getInstance_(getActivity(), this);
        this.caloriesCalculator = CaloriesCalculator.getInstance_(getActivity(), this);
        this.formatHelper = FormatHelper.getInstance_(getActivity(), this);
        init();
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

    @Override
    public void onViewChanged(HasViews hasViews) {
        this.donutView = hasViews.internalFindViewById(R.id.donutView);
        this.playPauseButton = hasViews.internalFindViewById(R.id.playPauseButton);
        this.km = hasViews.internalFindViewById(R.id.km);
        this.calories = hasViews.internalFindViewById(R.id.calories);
        this.activeInMin = hasViews.internalFindViewById(R.id.activeInMin);
        this.steps = hasViews.internalFindViewById(R.id.steps);
        this.target = hasViews.internalFindViewById(R.id.target);
        this.user = hasViews.internalFindViewById(R.id.user);
        if (this.user != null) {
            this.user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Unity.isAdLoaded()) {//unity fb
                        Unity.showInterstitial(getActivity(), new IInterstitialAdShowListener() {
                            @Override
                            public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {
                                openIntroActivity();
                                Unity.loadInterstitial(getActivity());//load unity ads
                            }

                            @Override
                            public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                            }

                        });
                        Unity.loadInterstitial(getActivity());//load unity ads
                    } else {
                        openIntroActivity();
                        Unity.loadInterstitial(getActivity());//load unity ads
                    }


                }
            });
        }
        if (this.playPauseButton != null) {
            this.playPauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Unity.isAdLoaded()) {//unity fb
                        Unity.showInterstitial(getActivity(), new IInterstitialAdShowListener() {
                            @Override
                            public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {
                                HomeFragment.this.changeServicePlayPause();
                                Unity.loadInterstitial(getActivity());//load unity ads
                            }

                            @Override
                            public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                            }

                        });
                        Unity.loadInterstitial(getActivity());//load unity ads
                    } else {
                        HomeFragment.this.changeServicePlayPause();

                        Unity.loadInterstitial(getActivity());//load unity ads
                    }
                }
            });
        }
        initViews();
    }

    
    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, BaseHomeFragment> {

        @Override
        public BaseHomeFragment build() {
            HomeFragment homeFragment_ = new HomeFragment();
            homeFragment_.setArguments(this.args);
            return homeFragment_;
        }
    }
}
