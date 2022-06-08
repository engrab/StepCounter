package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.mainActivities;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.R;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.DatabaseRepository;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.Step;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.BaseAlarmHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.AttributeUtils;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.BaseCalcuationHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.BaseFormatHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.BaseServiceHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.SharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses.BaseCaloriesCalculator;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses.BasePermissionService;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.fragments.BaseFragment;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.introActivities.IntroActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import me.zhanghai.android.materialplaypausedrawable.MaterialPlayPauseButton;
import me.zhanghai.android.materialplaypausedrawable.MaterialPlayPauseDrawable;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;


public class BaseHomeFragment extends BaseFragment {
    public static final int KPI_ANIMATION_DURATION = 600;
    TextView activeInMin;
    BaseAlarmHelper alarmHelper;
    SharedPref allPreferences;
    BaseCalcuationHelper calcuationHelper;
    TextView calories;
    BaseCaloriesCalculator caloriesCalculator;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();
    DatabaseRepository databaseRepository;
    private int defaultTextColor;
    DonutProgressView donutView;
    BaseFormatHelper formatHelper;
    TextView km;
    BasePermissionService permissionService;
    MaterialPlayPauseButton playPauseButton;
    private ReviewManager reviewManager;
    BaseServiceHelper serviceHelper;
    TextView steps;
    TextView target;
    ImageView user;
    UserSharedPref userPreferences;
    BaseViewModel viewModel;



    public static  void lambda$null$0(Task task) {
    }

    public static BaseHomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.home_fragment, viewGroup, false);
    }

    @Override 
    public void onResume() {
        super.onResume();
    }

    @Override 
    public void onPause() {
        super.onPause();
    }

    @Override 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void init() {
        this.reviewManager = ReviewManagerFactory.create(getContext());
        if (this.permissionService.isPermissionGranted("android.permission.ACTIVITY_RECOGNITION")) {
            this.serviceHelper.startService();
        } else {
            requestPermission();
        }
        showInAppReviewDialog();
    }

    public void initViews() {
        animateKPIs();
        this.defaultTextColor = this.steps.getCurrentTextColor();
        this.donutView.setCap(this.userPreferences.stepTarget().get().intValue());
        if (this.allPreferences.servicePaused().get().booleanValue()) {
            this.playPauseButton.jumpToState(MaterialPlayPauseDrawable.State.Play);
            setPauseState();
        } else {
            this.playPauseButton.jumpToState(MaterialPlayPauseDrawable.State.Pause);
            setPlayState();
        }
        this.target.setText(getResources().getString(R.string.targetParam, this.userPreferences.stepTarget().get()));
    }

    private void showInAppReviewDialog() {
        try {
            if (!this.allPreferences.ratingDialogShown().get().booleanValue() && Days.daysBetween(installDate(), LocalDateTime.now()).getDays() >= 3) {
                this.reviewManager.requestReviewFlow().addOnCompleteListener(new OnCompleteListener() { // from class: com.kewitschka.stepcounter.ui.main.-$$Lambda$MainFragment$zxXPJjn0xk-mX-9DKxzqdR13G18
                    @Override // com.google.android.play.core.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        BaseHomeFragment.this.lambda$showInAppReviewDialog$1$MainFragment(task);
                    }
                });
                this.allPreferences.ratingDialogShown().put(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void lambda$showInAppReviewDialog$1$MainFragment(Task task) {
        if (task.isSuccessful()) {
            this.reviewManager.launchReviewFlow(getActivity(), (ReviewInfo) task.getResult()).addOnCompleteListener(MainFragmentListener.INSTANCE);
        }
    }

    private LocalDateTime installDate() {
        try {
            return LocalDateTime.fromDateFields(new Date(getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).firstInstallTime));
        } catch (Exception unused) {
            return LocalDateTime.now();
        }
    }

    public void openIntroActivity() {
        IntroActivity.intent(this).start();
        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void setPauseState() {
        this.steps.setText(getResources().getString(R.string.paused));
        this.steps.setTextColor(SupportMenu.CATEGORY_MASK);
    }

    private void setPlayState() {
        this.steps.setText(this.viewModel.formattedSteps(this.databaseRepository.stepsToday()));
        this.steps.setTextColor(this.defaultTextColor);
    }

    public void changeServicePlayPause() {
        Boolean bool = this.allPreferences.servicePaused().get();
        this.allPreferences.servicePaused().put(Boolean.valueOf(!bool.booleanValue()));
        if (bool.booleanValue()) {
            this.serviceHelper.startService();
            this.playPauseButton.jumpToState(MaterialPlayPauseDrawable.State.Pause);
            setPlayState();
            return;
        }
        this.serviceHelper.stopService();
        this.playPauseButton.jumpToState(MaterialPlayPauseDrawable.State.Play);
        setPauseState();
    }

    private void subscribe() {
        this.compositeDisposable.add(this.databaseRepository.stepTodayStream().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).onBackpressureLatest().subscribe(new Consumer() { // from class: com.kewitschka.stepcounter.ui.main.-$$Lambda$MainFragment$BDp73LOCK7KImdhKEOuRpZ-RXi8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                BaseHomeFragment.this.updateStepLabels((Step) obj);
            }
        }));
    }

    private void updateDonut() {
        this.donutView.submitData(new ArrayList(Collections.singleton(new DonutSection("steps", AttributeUtils.getAttributeValue(getContext(), R.color.icons), this.databaseRepository.stepsToday()))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStepLabels(Step step) {
        float steps = step.getSteps();
        this.km.setText(this.formatHelper.formatKm(steps));
        this.activeInMin.setText(this.formatHelper.formatMinutes(steps));
        updateDonut();
        if (!this.allPreferences.servicePaused().get().booleanValue()) {
            this.steps.setText(this.viewModel.formattedSteps(steps));
        }
        this.calories.setText(this.formatHelper.formatCalories(steps));
    }

    @Override 
    public void onStart() {
        super.onStart();
        updateDonut();
        if (this.compositeDisposable.size() <= 0) {
            subscribe();
        }
    }

    private void animateKPIs() {
        YoYo.with(Techniques.ZoomIn).duration(600L).repeat(0).playOn(this.steps);
        YoYo.with(Techniques.ZoomIn).duration(600L).repeat(0).playOn(this.km);
        YoYo.with(Techniques.ZoomIn).duration(600L).repeat(0).playOn(this.calories);
        YoYo.with(Techniques.ZoomIn).duration(600L).repeat(0).playOn(this.activeInMin);
    }

    @Override 
    public void onStop() {
        super.onStop();
    }

    @Override 
    public void onDestroy() {
        super.onDestroy();
        this.compositeDisposable.dispose();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 29) {
            Dexter.withContext(getActivity()).withPermission("android.permission.ACTIVITY_RECOGNITION").withListener(new BasePermissionListener() { // from class: com.kewitschka.stepcounter.ui.main.MainFragment.1
                @Override // com.karumi.dexter.listener.single.BasePermissionListener, com.karumi.dexter.listener.single.PermissionListener
                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                    BaseHomeFragment.this.serviceHelper.startService();
                }
            }).check();
        }
    }
}
