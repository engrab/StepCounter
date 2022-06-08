package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.servicesClasses;

import android.content.Context;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.DatabaseRepository_;


public final class StepCalculator extends BaseStepCalculator {
    private Context context_;
    private Object rootFragment_;

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof StepCalculator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StepCalculator)) {
            return false;
        }
        StepCalculator stepCalculator_ = (StepCalculator) obj;
        if (!stepCalculator_.canEqual(this)) {
            return false;
        }
        Context context_ = getContext_();
        Context context_2 = stepCalculator_.getContext_();
        if (context_ != null ? !context_.equals(context_2) : context_2 != null) {
            return false;
        }
        Object rootFragment_ = getRootFragment_();
        Object rootFragment_2 = stepCalculator_.getRootFragment_();
        return rootFragment_ != null ? rootFragment_.equals(rootFragment_2) : rootFragment_2 == null;
    }

    @Override
    public int hashCode() {
        Context context_ = getContext_();
        int i = 43;
        int hashCode = context_ == null ? 43 : context_.hashCode();
        Object rootFragment_ = getRootFragment_();
        int i2 = (hashCode + 59) * 59;
        if (rootFragment_ != null) {
            i = rootFragment_.hashCode();
        }
        return i2 + i;
    }

    public void setContext_(Context context) {
        this.context_ = context;
    }

    public void setRootFragment_(Object obj) {
        this.rootFragment_ = obj;
    }

    @Override
    public String toString() {
        return "StepCalculator_(context_=" + getContext_() + ", rootFragment_=" + getRootFragment_() + ")";
    }

    public Context getContext_() {
        return this.context_;
    }

    public Object getRootFragment_() {
        return this.rootFragment_;
    }

    private StepCalculator(Context context) {
        this.context_ = context;
        init_();
    }

    private StepCalculator(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
        init_();
    }

    public static StepCalculator getInstance_(Context context) {
        return new StepCalculator(context);
    }

    public static StepCalculator getInstance_(Context context, Object obj) {
        return new StepCalculator(context, obj);
    }

    private void init_() {
        this.databaseRepository = DatabaseRepository_.getInstance_(this.context_);
        init();
    }

    public void rebind(Context context) {
        this.context_ = context;
        init_();
    }
}
