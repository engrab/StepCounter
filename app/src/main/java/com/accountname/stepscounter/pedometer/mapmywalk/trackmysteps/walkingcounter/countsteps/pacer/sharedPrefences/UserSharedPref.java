package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.sharedPrefences;

import android.content.Context;
import android.content.SharedPreferences;

import org.androidannotations.api.sharedpreferences.BooleanPrefEditorField;
import org.androidannotations.api.sharedpreferences.BooleanPrefField;
import org.androidannotations.api.sharedpreferences.EditorHelper;
import org.androidannotations.api.sharedpreferences.FloatPrefEditorField;
import org.androidannotations.api.sharedpreferences.FloatPrefField;
import org.androidannotations.api.sharedpreferences.IntPrefEditorField;
import org.androidannotations.api.sharedpreferences.IntPrefField;
import org.androidannotations.api.sharedpreferences.SharedPreferencesHelper;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;

import io.fabric.sdk.android.services.settings.AppSettingsData;


public final class UserSharedPref extends SharedPreferencesHelper {
    public UserSharedPref(Context context) {
        super(context.getSharedPreferences("UserPreferences", 0));
    }

    public UserPreferencesEditor_ edit() {
        return new UserPreferencesEditor_(getSharedPreferences());
    }

    public IntPrefField yearOfBirth() {
        return intField("yearOfBirth", 2000);
    }

    public IntPrefField weightInKg() {
        return intField("weightInKg", 65);
    }

    public IntPrefField sizeInCm() {
        return intField("sizeInCm", Opcodes.TABLESWITCH);
    }

    public IntPrefField stepTarget() {
        return intField("stepTarget", 5000);
    }

    public FloatPrefField stepSizeInM() {
        return floatField("stepSizeInM", 0.7f);
    }

    public BooleanPrefField configured() {
        return booleanField(AppSettingsData.STATUS_CONFIGURED, false);

    }

    public BooleanPrefField woman() {
        return booleanField("woman", true);
    }

    
    public static final class UserPreferencesEditor_ extends EditorHelper<UserPreferencesEditor_> {
        UserPreferencesEditor_(SharedPreferences sharedPreferences) {
            super(sharedPreferences);
        }

        public IntPrefEditorField<UserPreferencesEditor_> yearOfBirth() {
            return intField("yearOfBirth");
        }

        public IntPrefEditorField<UserPreferencesEditor_> weightInKg() {
            return intField("weightInKg");
        }

        public IntPrefEditorField<UserPreferencesEditor_> sizeInCm() {
            return intField("sizeInCm");
        }

        public IntPrefEditorField<UserPreferencesEditor_> stepTarget() {
            return intField("stepTarget");
        }

        public FloatPrefEditorField<UserPreferencesEditor_> stepSizeInM() {
            return floatField("stepSizeInM");
        }

        public BooleanPrefEditorField<UserPreferencesEditor_> configured() {
            return booleanField(AppSettingsData.STATUS_CONFIGURED);

        }

        public BooleanPrefEditorField<UserPreferencesEditor_> woman() {
            return booleanField("woman");
        }
    }
}
