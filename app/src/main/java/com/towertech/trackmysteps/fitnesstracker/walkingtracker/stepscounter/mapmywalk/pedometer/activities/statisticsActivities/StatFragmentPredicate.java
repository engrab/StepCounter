package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.statisticsActivities;

import com.annimon.stream.function.Predicate;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.Step;

public final class StatFragmentPredicate implements Predicate {
    public static final  StatFragmentPredicate INSTANCE = new StatFragmentPredicate();

    private  StatFragmentPredicate() {
    }

    @Override
    public final boolean test(Object obj) {
        return BaseStatsFragment.lambda$setAverageLabels$0((Step) obj);
    }
}
