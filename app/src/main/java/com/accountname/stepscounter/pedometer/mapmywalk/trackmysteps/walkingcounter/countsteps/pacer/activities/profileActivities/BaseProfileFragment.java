package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.activities.profileActivities;

import android.animation.Animator;
import android.os.Build;
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
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.R;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.AttributeUtils;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.BaseCalcuationHelper;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.SharedPref;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences.UserSharedPref;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses.BasePermissionService;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import it.sephiroth.android.library.numberpicker.NumberPicker;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.joda.time.LocalDate;


public class BaseProfileFragment extends Fragment {
    SharedPref allPreferences;
    BaseCalcuationHelper calcuationHelper;
    TextView description;
    Button grant;
    TextView header;
    ScrollView mainScrollView;
    ImageView man;
    CardView permissionLayout;
    BasePermissionService permissionService;
    NumberPicker size;
    NumberPicker stepTarget;
    RadioGroup themes;
    private Transformation transformation;
    UserSharedPref userPreferences;
    NumberPicker weight;
    ImageView woman;
    private boolean womanSelected;
    NumberPicker yearOfBirth;


    public void initViews() {
        this.header.setText(this.userPreferences.configured().get().booleanValue() ? R.string.profile : R.string.setup);
        this.description.setVisibility(this.userPreferences.configured().get().booleanValue() ? 8 : 0);
        this.transformation = new RoundedTransformationBuilder().cornerRadiusDp(3.0f).borderColor(AttributeUtils.getAttributeValue(getContext(), R.attr.customTextColor)).borderWidth(15.0f).oval(true).build();
        if (this.userPreferences.woman().get().booleanValue()) {
            selectWoman();
        } else {
            selectMan();
        }
        this.yearOfBirth.setMaxValue(LocalDate.now().getYear());
        this.yearOfBirth.setProgress(this.userPreferences.yearOfBirth().get().intValue());
        this.weight.setProgress(this.userPreferences.weightInKg().get().intValue());
        this.size.setProgress(this.userPreferences.sizeInCm().get().intValue());
        this.stepTarget.setProgress(this.userPreferences.stepTarget().get().intValue());
        if (this.permissionService.isPermissionGranted("android.permission.ACTIVITY_RECOGNITION")) {
            this.permissionLayout.setVisibility(8);
        }
        setThemeSelection();
        this.themes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.kewitschka.stepcounter.ui.profile.-$$Lambda$ProfileFragment$x3lHTIJfNf5159xezCbuTlyL4eg
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                BaseProfileFragment.this.lambda$initViews$0$ProfileFragment(radioGroup, i);
            }
        });
    }

    public  void lambda$initViews$0$ProfileFragment(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.darkModeNo /* 2131230860 */:
                this.allPreferences.theme().put(1);
                return;
            case R.id.darkModeSystem /* 2131230861 */:
                this.allPreferences.theme().put(0);
                return;
            case R.id.darkModeYes /* 2131230862 */:
                this.allPreferences.theme().put(2);
                return;
            default:
                return;
        }
    }

    private void setThemeSelection() {
        int intValue = this.allPreferences.theme().get().intValue();
        if (intValue == 0) {
            this.themes.check(R.id.darkModeSystem);
        } else if (intValue == 1) {
            this.themes.check(R.id.darkModeNo);
        } else if (intValue == 2) {
            this.themes.check(R.id.darkModeYes);
        }
    }

    public void selectWoman() {
        this.womanSelected = true;
        Picasso.get().load(R.drawable.ic_woman).transform(this.transformation).noPlaceholder().into(this.woman);
        Picasso.get().load(R.drawable.ic_man).noPlaceholder().into(this.man);
    }

    public  void lambda$scrollToBottom$1$ProfileFragment() {
        this.mainScrollView.fullScroll(Opcodes.IXOR);
    }

    public void scrollToBottom() {
        this.mainScrollView.post(new Runnable() { // from class: com.kewitschka.stepcounter.ui.profile.-$$Lambda$ProfileFragment$9iGN5aBUmkzW2OtPP5sO_iYrLeM
            @Override // java.lang.Runnable
            public final void run() {
                BaseProfileFragment.this.lambda$scrollToBottom$1$ProfileFragment();
            }
        });
    }

    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= 29) {
            Dexter.withContext(getActivity()).withPermission("android.permission.ACTIVITY_RECOGNITION").withListener(new BasePermissionListener() { // from class: com.kewitschka.stepcounter.ui.profile.ProfileFragment.1
                @Override // com.karumi.dexter.listener.single.BasePermissionListener, com.karumi.dexter.listener.single.PermissionListener
                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                }
            }).check();
        }
    }

    public void selectMan() {
        this.womanSelected = false;
        Picasso.get().load(R.drawable.ic_man).transform(this.transformation).noPlaceholder().into(this.man);
        Picasso.get().load(R.drawable.ic_woman).noPlaceholder().into(this.woman);
    }

    private void save() {
        this.userPreferences.yearOfBirth().put(Integer.valueOf(this.yearOfBirth.getProgress()));
        this.userPreferences.weightInKg().put(Integer.valueOf(this.weight.getProgress()));
        this.userPreferences.sizeInCm().put(Integer.valueOf(this.size.getProgress()));
        this.userPreferences.stepTarget().put(Integer.valueOf(this.stepTarget.getProgress()));
        this.userPreferences.stepSizeInM().put(Float.valueOf((float) this.calcuationHelper.sizeInCmToStepSize(this.size.getProgress())));
        this.userPreferences.woman().put(Boolean.valueOf(this.womanSelected));
        this.userPreferences.configured().put(true);
    }

    public void showGrantError() {
        YoYo.with(Techniques.Shake).duration(700L).repeat(2).onStart(new YoYo.AnimatorCallback() { // from class: com.kewitschka.stepcounter.ui.profile.-$$Lambda$ProfileFragment$GNhtPoYZxNBc9pozHBbnGZAFurM
            @Override // com.daimajia.androidanimations.library.YoYo.AnimatorCallback
            public final void call(Animator animator) {
                BaseProfileFragment.this.lambda$showGrantError$2$ProfileFragment(animator);
            }
        }).onEnd(new YoYo.AnimatorCallback() { // from class: com.kewitschka.stepcounter.ui.profile.-$$Lambda$ProfileFragment$RhOfRANQwAOV_zWUtBh-dtbNitI
            @Override // com.daimajia.androidanimations.library.YoYo.AnimatorCallback
            public final void call(Animator animator) {
                BaseProfileFragment.this.lambda$showGrantError$3$ProfileFragment(animator);
            }
        }).playOn(this.grant);
    }

    public  void lambda$showGrantError$2$ProfileFragment(Animator animator) {
        this.grant.setTextColor(SupportMenu.CATEGORY_MASK);
    }

    public  void lambda$showGrantError$3$ProfileFragment(Animator animator) {
        this.grant.setTextColor(-1);
    }

    @Override 
    public void onPause() {
        super.onPause();
        save();
    }

    public static BaseProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.profile_fragment, viewGroup, false);
    }

    @Override 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }
}
