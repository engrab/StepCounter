package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.List;

public interface StepDatabaseAccessor {
    Maybe<List<Step>> getAllSteps();

    Maybe<Float> getOffsetById(String str);

    Maybe<Step> getStepById(String str);

    Flowable<Step> getStepByIdStream(String str);

    Maybe<Float> getStepsById(String str);

    Maybe<List<Step>> getStepsForLastDays(int i);

    void insertOrUpdateStep(Step step);

    void updateOffset(String str, float f);

    void updateSteps(String str, float f);
}
