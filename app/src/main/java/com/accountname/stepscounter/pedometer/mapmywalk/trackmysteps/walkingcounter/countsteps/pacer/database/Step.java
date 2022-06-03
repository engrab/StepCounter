package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database;

import java.util.Objects;
import org.joda.time.LocalDateTime;


public class Step {
    public LocalDateTime date;
    public String id;
    public float offset;
    public float steps;

    
    public static class StepBuilder {
        private LocalDateTime date;
        private String id;
        private float offset;
        private float steps;

        StepBuilder() {
        }

        public Step build() {
            return new Step(this.id, this.steps, this.offset, this.date);
        }

        public StepBuilder date(LocalDateTime localDateTime) {
            this.date = localDateTime;
            return this;
        }

        public StepBuilder id(String str) {
            this.id = str;
            return this;
        }

        public StepBuilder offset(float f) {
            this.offset = f;
            return this;
        }

        public StepBuilder steps(float f) {
            this.steps = f;
            return this;
        }

        public String toString() {
            return "Step.StepBuilder(id=" + this.id + ", steps=" + this.steps + ", offset=" + this.offset + ", date=" + this.date + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Step(String str, float f, float f2, LocalDateTime localDateTime) {
        Objects.requireNonNull(str, "id is marked non-null but is null");
        this.id = str;
        this.steps = f;
        this.offset = f2;
        this.date = localDateTime;
    }

    public static StepBuilder builder() {
        return new StepBuilder();
    }

    public StepBuilder toBuilder() {
        return new StepBuilder().id(this.id).steps(this.steps).offset(this.offset).date(this.date);
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof Step;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Step)) {
            return false;
        }
        Step step = (Step) obj;
        if (!step.canEqual(this) || Float.compare(getSteps(), step.getSteps()) != 0 || Float.compare(getOffset(), step.getOffset()) != 0) {
            return false;
        }
        String id = getId();
        String id2 = step.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        LocalDateTime date = getDate();
        LocalDateTime date2 = step.getDate();
        return date != null ? date.equals(date2) : date2 == null;
    }

    public int hashCode() {
        int floatToIntBits = ((Float.floatToIntBits(getSteps()) + 59) * 59) + Float.floatToIntBits(getOffset());
        String id = getId();
        int i = 43;
        int hashCode = (floatToIntBits * 59) + (id == null ? 43 : id.hashCode());
        LocalDateTime date = getDate();
        int i2 = hashCode * 59;
        if (date != null) {
            i = date.hashCode();
        }
        return i2 + i;
    }

    public void setDate(LocalDateTime localDateTime) {
        this.date = localDateTime;
    }

    public void setId(String str) {
        Objects.requireNonNull(str, "id is marked non-null but is null");
        this.id = str;
    }

    public void setOffset(float f) {
        this.offset = f;
    }

    public void setSteps(float f) {
        this.steps = f;
    }

    public String toString() {
        return "Step(id=" + getId() + ", steps=" + getSteps() + ", offset=" + getOffset() + ", date=" + getDate() + ")";
    }

    public String getId() {
        return this.id;
    }

    public float getSteps() {
        return this.steps;
    }

    public float getOffset() {
        return this.offset;
    }

    public LocalDateTime getDate() {
        return this.date;
    }
}
