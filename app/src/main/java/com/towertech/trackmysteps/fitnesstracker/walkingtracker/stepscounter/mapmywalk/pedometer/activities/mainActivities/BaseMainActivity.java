package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.mainActivities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.R;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.introActivities.IntroActivity;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.mixActivities.PrivacyActivity;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.statisticsActivities.BaseStatsFragment;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.SharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;


public class BaseMainActivity extends AppCompatActivity {

    SharedPref allPreferences;
    UserSharedPref userPreferences;
    SpeedDialView speedDialView;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!this.userPreferences.configured().get().booleanValue()) {
            IntroActivity.intent(this).start();
            return;
        }
        setPreferredTheme();
        setContentView(R.layout.main_activity);

        if (bundle == null) {
            getWindow().clearFlags(1024);
            getSupportFragmentManager().beginTransaction().add(R.id.container, BaseHomeFragment.newInstance()).add(R.id.container, BaseStatsFragment.newInstance()).commitNow();
        }
        speedDialView = findViewById(R.id.speedDial);
        speedDialView.inflate(R.menu.menu_share);

        Drawable drawable = AppCompatResources.getDrawable(this, R.drawable.ic_baseline_share_24);
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.navShare, drawable)
                .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null))
                .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.white, null))
                .setLabel(R.string.share)
                .create());

        Drawable rate = AppCompatResources.getDrawable(this, R.drawable.ic_baseline_star_rate_24);
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.navRate, rate)
                .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.primary, null))
                .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.white, null))
                .setLabel(R.string.rate)
                .create());

        Drawable more = AppCompatResources.getDrawable(this, R.drawable.ic_baseline_apps_24);
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.navMore, more)
                .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.yellow, null))
                .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.white, null))
                .setLabel(R.string.more)
                .create());

        Drawable privacy = AppCompatResources.getDrawable(this, R.drawable.ic_baseline_privacy_tip_24);
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.navPrivacy, privacy)
                .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null))
                .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.white, null))
                .setLabel(R.string.privacy_policy)
                .create());
//        speedDialView.addActionItem(
//                SpeedDialActionItem.Builder(R.id.navShare, drawable)
//                        .setFabBackgroundColor(ResourcesCompat.getColor(resources, R.color.material_green_500, theme))
//                        .setLabel(R.string.label_add_action)
//                        .setLabelBackgroundColor(Color.TRANSPARENT)
//                        .create(),
//                )

        speedDialView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {

            }
        });
        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()) {
                    case R.id.navPrivacy:
                        Intent intent1 = new Intent(BaseMainActivity.this, PrivacyActivity.class);
                        intent1.putExtra("key", true);
                        startActivity(intent1);
                        finish();

                        return true;
                    case R.id.navShare:
                        try {
                            Intent intent = new Intent("android.intent.action.SEND");
                            intent.setType("text/plain");
                            intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
                            intent.putExtra("android.intent.extra.TEXT", "Count & Track Steps Pedometer\n share with family and friends \nhttps://play.google.com/store/apps/details?id=" + getPackageName());
                            startActivity(Intent.createChooser(intent, "Share via"));
                        } catch (Exception unused) {
                        }
                        return true;
                    case R.id.navRate:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=" + getPackageName())));
                        } catch (android.content.ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                        }
                        return true;
                    case R.id.navMore:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Tower+Tech+Games+And+Apps")));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Tower+Tech+Games+And+Apps")));
                        }
                        return true;
                }
                return false;
            }
        });
    }

    private void setPreferredTheme() {
        int intValue = this.allPreferences.theme().get().intValue();
        if (intValue == 0) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        } else if (intValue == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (intValue == 2) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

//    public void initViews() {
//        this.adView.loadAd(new AdRequest.Builder().build());
//    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
