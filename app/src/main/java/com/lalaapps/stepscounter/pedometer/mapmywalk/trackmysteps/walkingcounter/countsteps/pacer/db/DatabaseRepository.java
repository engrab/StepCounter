package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db;

import android.content.Context;
import androidx.room.Room;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.LocalDateTime;


public class DatabaseRepository {
    private static final String DEFAULT_SERIAL = "default_serial";
    private BlockingResolver<List<Step>> allStepResolver;
    Context context;
    private RoomDatabase database;
    private StepDatabaseAccessor dbAccessor;
    private BlockingResolver<Step> stepResolver;
    private BlockingResolver<Float> stepsTodayResolver;

    public void init() {
        RoomDatabase roomDatabase = (RoomDatabase) Room.databaseBuilder(this.context, RoomDatabase.class, "stepCounter").build();
        this.database = roomDatabase;
        this.dbAccessor = roomDatabase.accessor();
        this.stepResolver = new BlockingResolver<>();
        this.allStepResolver = new BlockingResolver<>();
        this.stepsTodayResolver = new BlockingResolver<>();
    }

    public Maybe<Float> getStepsById(String str) {
        return this.dbAccessor.getStepsById(str);
    }

    public void insertOrUpdateStep(Step step) {
        this.dbAccessor.insertOrUpdateStep(step);
    }

    public void updateOffset(float f) {
        this.dbAccessor.updateOffset(idForToday(), f);
    }

    public void updateSteps(float f) {
        this.dbAccessor.updateSteps(idForToday(), f);
    }

    public Maybe<List<Step>> getAllSteps() {
        return this.dbAccessor.getAllSteps();
    }

    public List<Step> getStepsForLastDays(int i) {
        return this.allStepResolver.resolve(this.dbAccessor.getStepsForLastDays(i), new ArrayList());
    }

    public List<Step> allStepsOrDefault() {
        return this.allStepResolver.resolve(this.dbAccessor.getAllSteps(), new ArrayList());
    }

    public Step stepToday() {
        return this.stepResolver.resolve(this.dbAccessor.getStepById(idForToday()), Step.builder().id(idForToday()).build());
    }

    public Flowable<Step> stepTodayStream() {
        return this.dbAccessor.getStepByIdStream(idForToday());
    }

    public float stepsToday() {
        return this.stepsTodayResolver.resolve(this.dbAccessor.getStepsById(idForToday()), Float.valueOf(0.0f)).floatValue();
    }

    public float offsetToday() {
        return this.stepsTodayResolver.resolve(this.dbAccessor.getOffsetById(idForToday()), Float.valueOf(0.0f)).floatValue();
    }

    public String idForToday() {
        LocalDateTime now = LocalDateTime.now();
        return String.format(Locale.getDefault(), "steps_%d_%d_%d", Integer.valueOf(now.dayOfMonth().get()), Integer.valueOf(now.monthOfYear().get()), Integer.valueOf(now.year().get()));
    }
}
