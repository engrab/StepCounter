package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.ui.statistics;

import com.annimon.stream.function.Predicate;
import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db.Step;

public final class StatFragmentPredicate implements Predicate {
    public static final  StatFragmentPredicate INSTANCE = new StatFragmentPredicate();

    private  StatFragmentPredicate() {
    }

    @Override
    public final boolean test(Object obj) {
        return BaseStatsFragment.lambda$setAverageLabels$0((Step) obj);
    }
}
