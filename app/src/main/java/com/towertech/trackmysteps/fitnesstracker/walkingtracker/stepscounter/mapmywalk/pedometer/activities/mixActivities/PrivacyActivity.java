package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.mixActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.R;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.mainActivities.MainActivity;

public class PrivacyActivity extends AppCompatActivity {

    public static final String URL = "https://sites.google.com/view/privacy-policy-towertech-apps/home";
    private Toolbar toolbar;
    private WebView privacy_policy;
    private ProgressBar progressBar_policy;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        button = findViewById(R.id.btnGo);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            boolean key = extras.getBoolean("key");
            if (key){
                button.setVisibility(View.GONE);
            }
        }


        toolbar = this.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.privacy_policy));
        this.setSupportActionBar(toolbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("firstTime",MODE_PRIVATE).edit();
                editor.putBoolean("first", true);
                editor.apply();
                startActivity(new Intent(PrivacyActivity.this, MainActivity.class));
                finish();
            }
        });


        progressBar_policy = findViewById(R.id.progressBar_policy);

        privacy_policy = findViewById(R.id.privacy_policy);
        privacy_policy.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                toolbar.setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if (progress == 100) {
                    toolbar.setTitle(R.string.privacy_policy);
                    progressBar_policy.setVisibility(View.GONE);
                }
            }
        });
        privacy_policy.getSettings().setJavaScriptEnabled(true);
        privacy_policy.loadUrl(URL);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PrivacyActivity.this, MainActivity.class));
        finish();
    }
}