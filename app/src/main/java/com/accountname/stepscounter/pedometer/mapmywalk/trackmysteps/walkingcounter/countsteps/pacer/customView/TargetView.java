package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.R;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.AttributeUtils;
import java.util.ArrayList;
import java.util.Collections;
import org.joda.time.LocalDateTime;


public class TargetView extends LinearLayout {
    private LocalDateTime dateTime;
    DonutProgressView donut;
    private float steps;
    private int target;
    ImageView tick;
    private String title;
    TextView titleView;

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public TargetView(Context context, String str, int i, float f, LocalDateTime localDateTime) {
        this(context, null);
        this.title = str;
        this.target = i;
        this.steps = f;
        this.dateTime = localDateTime;
        init();
    }

    public TargetView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TargetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public TargetView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private void init() {
        inflate(getContext(), R.layout.target_view, this);
        this.titleView = (TextView) findViewById(R.id.titleView);
        this.donut = (DonutProgressView) findViewById(R.id.donut);
        ImageView imageView = (ImageView) findViewById(R.id.tick);
        this.tick = imageView;
        imageView.setVisibility(this.steps >= ((float) this.target) ? VISIBLE : GONE);
        this.donut.setCap(this.target);
        this.donut.submitData(new ArrayList(Collections.singleton(new DonutSection("steps", AttributeUtils.getAttributeValue(getContext(), R.attr.customTextColor), this.steps))));
        this.titleView.setText(this.title);
    }

    public void setSteps(float f) {
        this.steps = f;
        this.donut.submitData(new ArrayList(Collections.singleton(new DonutSection("steps", AttributeUtils.getAttributeValue(getContext(), R.attr.customTextColor), f))));
        this.tick.setVisibility(f >= ((float) this.target) ? 0 : 8);
    }
}
