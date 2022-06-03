package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.servicesClasses;

import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database.DatabaseRepository;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database.Step;
import org.joda.time.LocalDateTime;


public class BaseStepCalculator {
    DatabaseRepository databaseRepository;
    private float newStepsToday;
    private float offsetForToday;
    private float stepsToday;

    protected boolean canEqual(Object obj) {
        return obj instanceof BaseStepCalculator;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseStepCalculator)) {
            return false;
        }
        BaseStepCalculator stepCalculator = (BaseStepCalculator) obj;
        if (!stepCalculator.canEqual(this) || Float.compare(getStepsToday(), stepCalculator.getStepsToday()) != 0 || Float.compare(getNewStepsToday(), stepCalculator.getNewStepsToday()) != 0 || Float.compare(getOffsetForToday(), stepCalculator.getOffsetForToday()) != 0) {
            return false;
        }
        DatabaseRepository databaseRepository = getDatabaseRepository();
        DatabaseRepository databaseRepository2 = stepCalculator.getDatabaseRepository();
        return databaseRepository != null ? databaseRepository.equals(databaseRepository2) : databaseRepository2 == null;
    }

    public int hashCode() {
        int floatToIntBits = ((((Float.floatToIntBits(getStepsToday()) + 59) * 59) + Float.floatToIntBits(getNewStepsToday())) * 59) + Float.floatToIntBits(getOffsetForToday());
        DatabaseRepository databaseRepository = getDatabaseRepository();
        return (floatToIntBits * 59) + (databaseRepository == null ? 43 : databaseRepository.hashCode());
    }

    public void setDatabaseRepository(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public void setNewStepsToday(float f) {
        this.newStepsToday = f;
    }

    public void setOffsetForToday(float f) {
        this.offsetForToday = f;
    }

    public void setStepsToday(float f) {
        this.stepsToday = f;
    }

    public String toString() {
        return "StepCalculator(databaseRepository=" + getDatabaseRepository() + ", stepsToday=" + getStepsToday() + ", newStepsToday=" + getNewStepsToday() + ", offsetForToday=" + getOffsetForToday() + ")";
    }

    public DatabaseRepository getDatabaseRepository() {
        return this.databaseRepository;
    }

    public float getStepsToday() {
        return this.stepsToday;
    }

    public float getNewStepsToday() {
        return this.newStepsToday;
    }

    public float getOffsetForToday() {
        return this.offsetForToday;
    }

    public void init() {
        this.stepsToday = this.databaseRepository.stepsToday();
        this.offsetForToday = this.databaseRepository.offsetToday();
    }

    public float updateStepsSinceBoot(float f) {
        float f2 = this.offsetForToday;
        if (f2 > 0.0f && f < f2) {
            float stepsToday = this.databaseRepository.stepsToday() * (-1.0f);
            this.offsetForToday = stepsToday;
            this.databaseRepository.updateOffset(stepsToday);
        }
        float f3 = this.offsetForToday;
        if (f3 == 0.0f) {
            this.newStepsToday = 0.0f;
            this.offsetForToday = f;
            this.databaseRepository.insertOrUpdateStep(Step.builder().id(this.databaseRepository.idForToday()).steps(this.newStepsToday).offset(this.offsetForToday).date(LocalDateTime.now()).build());
        } else {
            this.newStepsToday = f - f3;
        }
        return this.newStepsToday;
    }

    public float newStepsToday() {
        float f = this.stepsToday;
        float f2 = this.newStepsToday;
        if (f != f2) {
            this.stepsToday = f2;
            this.databaseRepository.updateSteps(f2);
        }
        return this.newStepsToday;
    }

    public void reset() {
        this.stepsToday = 0.0f;
        this.newStepsToday = 0.0f;
        this.offsetForToday = 0.0f;
    }
}
