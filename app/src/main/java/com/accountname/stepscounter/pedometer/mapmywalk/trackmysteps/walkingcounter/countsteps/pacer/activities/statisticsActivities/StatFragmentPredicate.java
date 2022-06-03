package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.activities.statisticsActivities;

import com.annimon.stream.function.Predicate;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database.Step;

public final class StatFragmentPredicate implements Predicate {
    public static final  StatFragmentPredicate INSTANCE = new StatFragmentPredicate();

    private  StatFragmentPredicate() {
    }

    @Override
    public final boolean test(Object obj) {
        return BaseStatsFragment.lambda$setAverageLabels$0((Step) obj);
    }
}
