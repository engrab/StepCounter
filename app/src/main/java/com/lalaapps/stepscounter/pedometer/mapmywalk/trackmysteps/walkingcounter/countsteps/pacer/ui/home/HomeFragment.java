package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.R;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.ads.AdsUtils;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db.DatabaseRepository_;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.AlarmHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.CalcuationHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.FormatHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.ServiceHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.SharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.UserSharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services.CaloriesCalculator;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services.PermissionService;
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
    InterstitialAd mInterstitialAd;
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
                    mInterstitialAd = AdsUtils.getInterstitial();
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(requireActivity());
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                openIntroActivity();
                                AdsUtils.loadInterstitial(getContext());//load admob ad
                            }
                        });

                    } else {
                        openIntroActivity();
                        AdsUtils.loadInterstitial(getContext());//load admob ad

                    }

                }
            });
        }
        if (this.playPauseButton != null) {
            this.playPauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mInterstitialAd = AdsUtils.getInterstitial();
                    if (mInterstitialAd != null) {//admob
                        mInterstitialAd.show(requireActivity());
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                HomeFragment.this.changeServicePlayPause();
                                AdsUtils.loadInterstitial(getContext());//load admob ad
                            }
                        });

                    } else {
                        HomeFragment.this.changeServicePlayPause();
                        AdsUtils.loadInterstitial(getContext());//load admob ad

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
