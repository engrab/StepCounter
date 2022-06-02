package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helper;

import android.content.Context;
import android.util.TypedValue;


public class AttributeUtils {
    private static TypedValue typedValue = new TypedValue();

    public static int getAttributeValue(Context context, int i) {
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }
}
