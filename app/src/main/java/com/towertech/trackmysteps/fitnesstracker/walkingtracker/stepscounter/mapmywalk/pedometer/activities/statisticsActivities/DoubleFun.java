package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.statisticsActivities;

import com.annimon.stream.function.ToDoubleFunction;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.Step;

/* compiled from: lambda */
/* renamed from: com.kewitschka.stepcounter.ui.stats.-$$Lambda$VM9csOpp2S2tBniuxXn6ehbE_9k  reason: invalid class name */

public final  class DoubleFun implements ToDoubleFunction {
    public static final DoubleFun INSTANCE = new DoubleFun();

    private DoubleFun() {
    }

    @Override // com.annimon.stream.function.ToDoubleFunction
    public final double applyAsDouble(Object obj) {
        return ((Step) obj).getSteps();
    }
}
