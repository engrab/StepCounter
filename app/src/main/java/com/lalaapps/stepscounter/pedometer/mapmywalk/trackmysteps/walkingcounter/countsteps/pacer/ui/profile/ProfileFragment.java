package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.R;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper.CalcuationHelper;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.SharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPref.UserSharedPref;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.services.PermissionService;
import it.sephiroth.android.library.numberpicker.NumberPicker;
import java.util.HashMap;
import java.util.Map;
import org.androidannotations.api.bean.BeanHolder;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class ProfileFragment extends BaseProfileFragment implements BeanHolder, HasViews, OnViewChangedListener {
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
        this.woman = null;
        this.man = null;
        this.weight = null;
        this.size = null;
        this.stepTarget = null;
        this.yearOfBirth = null;
        this.permissionLayout = null;
        this.header = null;
        this.description = null;
        this.grant = null;
        this.mainScrollView = null;
        this.themes = null;
    }

    private void init_(Bundle bundle) {
        this.userPreferences = new UserSharedPref(getActivity());
        this.allPreferences = new SharedPref(getActivity());
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        this.permissionService = PermissionService.getInstance_(getActivity(), this);
        this.calcuationHelper = CalcuationHelper.getInstance_(getActivity(), this);
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
        this.woman = (ImageView) hasViews.internalFindViewById(R.id.woman);
        this.man = (ImageView) hasViews.internalFindViewById(R.id.man);
        this.weight = (NumberPicker) hasViews.internalFindViewById(R.id.weight);
        this.size = (NumberPicker) hasViews.internalFindViewById(R.id.size);
        this.stepTarget = (NumberPicker) hasViews.internalFindViewById(R.id.stepTarget);
        this.yearOfBirth = (NumberPicker) hasViews.internalFindViewById(R.id.yearOfBirth);
        this.permissionLayout = (CardView) hasViews.internalFindViewById(R.id.permissionLayout);
        this.header = (TextView) hasViews.internalFindViewById(R.id.header);
        this.description = (TextView) hasViews.internalFindViewById(R.id.description);
        this.grant = (Button) hasViews.internalFindViewById(R.id.grant);
        this.mainScrollView = (ScrollView) hasViews.internalFindViewById(R.id.mainScrollView);
        this.themes = (RadioGroup) hasViews.internalFindViewById(R.id.themes);
        if (this.woman != null) {
            this.woman.setOnClickListener(new View.OnClickListener() { // from class: com.kewitschka.stepcounter.ui.profile.ProfileFragment_.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ProfileFragment.this.selectWoman();
                }
            });
        }
        if (this.grant != null) {
            this.grant.setOnClickListener(new View.OnClickListener() { // from class: com.kewitschka.stepcounter.ui.profile.ProfileFragment_.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ProfileFragment.this.requestPermission();
                }
            });
        }
        if (this.man != null) {
            this.man.setOnClickListener(new View.OnClickListener() { // from class: com.kewitschka.stepcounter.ui.profile.ProfileFragment_.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ProfileFragment.this.selectMan();
                }
            });
        }
        initViews();
    }

    
    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, BaseProfileFragment> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.androidannotations.api.builder.FragmentBuilder
        public BaseProfileFragment build() {
            ProfileFragment profileFragment_ = new ProfileFragment();
            profileFragment_.setArguments(this.args);
            return profileFragment_;
        }
    }
}
