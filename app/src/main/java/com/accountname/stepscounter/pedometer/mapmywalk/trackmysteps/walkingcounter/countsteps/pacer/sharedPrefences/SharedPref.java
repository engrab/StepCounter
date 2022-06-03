package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences;

import android.content.Context;
import android.content.SharedPreferences;
import org.androidannotations.api.sharedpreferences.BooleanPrefEditorField;
import org.androidannotations.api.sharedpreferences.BooleanPrefField;
import org.androidannotations.api.sharedpreferences.EditorHelper;
import org.androidannotations.api.sharedpreferences.IntPrefEditorField;
import org.androidannotations.api.sharedpreferences.IntPrefField;
import org.androidannotations.api.sharedpreferences.SharedPreferencesHelper;
import org.androidannotations.api.sharedpreferences.StringPrefEditorField;
import org.androidannotations.api.sharedpreferences.StringPrefField;


public final class SharedPref extends SharedPreferencesHelper {
    public SharedPref(Context context) {
        super(context.getSharedPreferences("AllPreferences", 0));
    }

    public AllPreferencesEditor_ edit() {
        return new AllPreferencesEditor_(getSharedPreferences());
    }

    public IntPrefField statsDaysIndex() {
        return intField("statsDaysIndex", 0);
    }

    public BooleanPrefField servicePaused() {
        return booleanField("servicePaused", false);
    }

    public IntPrefField theme() {
        return intField("theme", 0);
    }

    public StringPrefField lastStepGoalNotification() {
        return stringField("lastStepGoalNotification", "");
    }

    public BooleanPrefField ratingDialogShown() {
        return booleanField("ratingDialogShown", false);
    }

    
    public static final class AllPreferencesEditor_ extends EditorHelper<AllPreferencesEditor_> {
        AllPreferencesEditor_(SharedPreferences sharedPreferences) {
            super(sharedPreferences);
        }

        public IntPrefEditorField<AllPreferencesEditor_> statsDaysIndex() {
            return intField("statsDaysIndex");
        }

        public BooleanPrefEditorField<AllPreferencesEditor_> servicePaused() {
            return booleanField("servicePaused");
        }

        public IntPrefEditorField<AllPreferencesEditor_> theme() {
            return intField("theme");
        }

        public StringPrefEditorField<AllPreferencesEditor_> lastStepGoalNotification() {
            return stringField("lastStepGoalNotification");
        }

        public BooleanPrefEditorField<AllPreferencesEditor_> ratingDialogShown() {
            return booleanField("ratingDialogShown");
        }
    }
}
