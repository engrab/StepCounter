package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.activities.statisticsActivities;

import android.content.Context;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.R;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.BaseFormatHelper;
import com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.helperClasses.FormatHelper;


public class MarkerTooltip extends MarkerView {
    private BaseFormatHelper formatHelper;
    private MPPointF mOffset;
    private TextView steps = (TextView) findViewById(R.id.steps);
    private TextView km = (TextView) findViewById(R.id.km);
    private TextView min = (TextView) findViewById(R.id.min);
    private TextView calories = (TextView) findViewById(R.id.calories);

    public MarkerTooltip(Context context, int i) {
        super(context, i);
        this.formatHelper = FormatHelper.getInstance_(context);
    }

    @Override
    public void refreshContent(Entry entry, Highlight highlight) {
        try {
            float y = entry.getY();
            this.steps.setText(this.formatHelper.formatSteps(y));
            this.km.setText(this.formatHelper.formatKm(y));
            this.min.setText(this.formatHelper.formatMinutes(y));
            this.calories.setText(this.formatHelper.formatCalories(y));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.refreshContent(entry, highlight);
    }

    @Override
    public MPPointF getOffset() {
        if (this.mOffset == null) {
            this.mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
        }
        return this.mOffset;
    }
}
