package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.introActivities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.github.appintro.AppIntro2;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.R;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses.BasePermissionService;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.mainActivities.MainActivity;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.profileActivities.BaseProfileFragment;

public class BaseIntro extends AppIntro2 {
    BasePermissionService permissionService;
    private BaseProfileFragment baseProfileFragment;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().clearFlags(1024);
        BaseProfileFragment newInstance = BaseProfileFragment.newInstance();
        this.baseProfileFragment = newInstance;
        addSlide(newInstance);
    }

    @Override
    protected void onSkipPressed(Fragment fragment) {
        super.onSkipPressed(fragment);
    }

    @Override
    protected void onDonePressed(Fragment fragment) {
        super.onDonePressed(fragment);
        if (!this.permissionService.isPermissionGranted("android.permission.ACTIVITY_RECOGNITION")) {
            this.baseProfileFragment.scrollToBottom();
            this.baseProfileFragment.showGrantError();
            return;
        }
        openMainActivity();
    }

    private void openMainActivity() {
        MainActivity.intent(this).start();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        openMainActivity();
    }
}
