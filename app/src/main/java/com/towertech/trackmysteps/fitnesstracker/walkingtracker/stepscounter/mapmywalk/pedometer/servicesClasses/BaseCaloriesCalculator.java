package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses;

import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.BaseCalcuationHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.enums.Gender;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.LocalDate;
import org.joda.time.Years;


public class BaseCaloriesCalculator {
    BaseCalcuationHelper calcuationHelper;
    UserSharedPref userPreferences;

    private float calculateDistanceTravelledInKM(float f, float f2) {
        return (f * f2) / 1000.0f;
    }

    private float convertKilocaloriesToMlKmin(float f, float f2) {
        return (((f / 1440.0f) / 5.0f) / f2) * 1000.0f;
    }

    private float convertKmsToMiles(float f) {
        return (float) (f * 0.621371d);
    }

    public float caloriesFromSteps(float f) {
        return calculateEnergyExpenditure(this.userPreferences.sizeInCm().get().intValue(), this.userPreferences.weightInKg().get().intValue(), this.userPreferences.woman().get().booleanValue() ? Gender.FEMALE : Gender.MALE, this.calcuationHelper.stepsToActiveInMinute(f), f, this.userPreferences.stepSizeInM().get().floatValue());
    }

    private float calculateEnergyExpenditure(float f, float f2, Gender gender, float f3, float f4, float f5) {
        LocalDate now = LocalDate.now();
        float f6 = f3 / 60.0f;
        return getMetForActivity(convertKmsToMiles(calculateDistanceTravelledInKM(f4, f5)) / f6) * (3.5f / convertKilocaloriesToMlKmin(harrisBenedictRmr(gender, f2, Years.yearsBetween(now.withYear(this.userPreferences.yearOfBirth().get().intValue()), now).getYears(), f), f2)) * f6 * f2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0034, code lost:
        if (r1.get(5) > r0.get(5)) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private float getAgeFromDateOfBirth(Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        if (!calendar2.after(calendar)) {
            int i = calendar.get(1) - calendar2.get(1);
            int i2 = calendar.get(2);
            int i3 = calendar2.get(2);
            if (i3 <= i2) {
                if (i2 == i3) {
                }
                return i;
            }
            i--;
            return i;
        }
        throw new IllegalArgumentException("Can't be born in the future");
    }

    private float getMetForActivity(float f) {
        double roundAvoid = this.calcuationHelper.roundAvoid(f, 1);
        if (f < 2.0f) {
            return 2.0f;
        }
        if (f == 2.0f) {
            return 2.8f;
        }
        if (this.calcuationHelper.betweenDouble(roundAvoid, 2.1d, 2.7d)) {
            return 3.0f;
        }
        if (this.calcuationHelper.betweenDouble(roundAvoid, 2.8d, 3.3d)) {
            return 3.5f;
        }
        if (this.calcuationHelper.betweenDouble(roundAvoid, 3.4d, 3.5d)) {
            return 4.3f;
        }
        if (this.calcuationHelper.betweenDouble(roundAvoid, 3.6d, 4.0d)) {
            return 5.0f;
        }
        if (this.calcuationHelper.betweenDouble(roundAvoid, 4.1d, 4.5d)) {
            return 7.0f;
        }
        if (this.calcuationHelper.betweenDouble(roundAvoid, 4.6d, 5.0d)) {
            return 8.3f;
        }
        return roundAvoid > 5.0d ? 9.8f : 0.0f;
    }

    private float harrisBenedictRmr(Gender gender, float f, float f2, float f3) {
        float f4;
        float f5;
        if (gender == Gender.FEMALE) {
            f4 = (f3 * 1.8496f) + 655.0955f + (f * 9.5634f);
            f5 = 4.6756f;
        } else {
            f4 = (f3 * 5.0033f) + 66.473f + (f * 13.7516f);
            f5 = 6.755f;
        }
        return f4 - (f2 * f5);
    }
}
