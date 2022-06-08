package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses;

import android.content.Context;
import android.util.TypedValue;


public class AttributeUtils {
    private static TypedValue typedValue = new TypedValue();

    public static int getAttributeValue(Context context, int i) {
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }
}
