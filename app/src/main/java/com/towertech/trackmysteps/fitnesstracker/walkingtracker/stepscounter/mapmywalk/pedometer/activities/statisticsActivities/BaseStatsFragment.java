package com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.activities.statisticsActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSpinner;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.R;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.customView.RoundedBarChartRenderer;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.DatabaseRepository;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.database.Step;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.AttributeUtils;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.helperClasses.BaseFormatHelper;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.SharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.sharedPrefences.UserSharedPref;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.fragments.BaseFragment;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.customView.TargetView;
import com.towertech.trackmysteps.fitnesstracker.walkingtracker.stepscounter.mapmywalk.pedometer.utilsClasses.DateAndTimeUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.joda.time.LocalDateTime;


public class BaseStatsFragment extends BaseFragment {
    public static final float CHART_TEXT_SIZE = 12.0f;
    public static final String DUMMY_ID = "dummy";
    public static final int NUMBER_OF_LAST_DAYS_FROM_DB = 30;
    SharedPref allPreferences;
    TextView averageCalories;
    TextView averageKm;
    TextView averageMin;
    TextView averageSteps;
    BarChart chart;
    private BarData data;
    private BarDataSet dataSet;
    DatabaseRepository databaseRepository;
    DateAndTimeUtils dateAndTimeUtils;
    AppCompatSpinner daysSpinner;
    BaseFormatHelper formatHelper;
    private List<Step> stepsForLastDays;
    LinearLayout targetViewContainer;
    UserSharedPref userPreferences;
    int[] days = {7, 30};
    private ArrayList<BarEntry> entries = new ArrayList<>();

    public static BaseStatsFragment newInstance() {
        return new StatsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.stats_fragment, viewGroup, false);
    }

    @Override 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override 
    public void onStart() {
        super.onStart();
    }

    @Override 
    public void onResume() {
        super.onResume();
        updateToday();
        setAverageLabels();
    }

    public void initViews() {
        this.daysSpinner.setAdapter((SpinnerAdapter) new ArrayAdapter(getContext(), (int) R.layout.spinner_item, new ArrayList(Arrays.asList(getResources().getString(R.string.one_week), getResources().getString(R.string.one_month)))));
        this.daysSpinner.setSelection(this.allPreferences.statsDaysIndex().get().intValue());
        this.daysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.kewitschka.stepcounter.ui.stats.StatsFragment.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != BaseStatsFragment.this.allPreferences.statsDaysIndex().get().intValue()) {
                    BaseStatsFragment.this.allPreferences.statsDaysIndex().put(Integer.valueOf(i));
                    BaseStatsFragment.this.refresh();
                }
            }
        });
        showLineChart(this.days[this.allPreferences.statsDaysIndex().get().intValue()]);
        initTargetViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refresh() {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    private void showLineChart(final int i) {
        LocalDateTime localDateTime;
        int i2 = 30;
        List<Step> stepsForLastDays = this.databaseRepository.getStepsForLastDays(30);
        this.stepsForLastDays = stepsForLastDays;
        boolean z = true;
        if (stepsForLastDays.size() < 30) {
            if (this.stepsForLastDays.size() > 0) {
                List<Step> list = this.stepsForLastDays;
                localDateTime = list.get(list.size() - 1).getDate();
            } else {
                localDateTime = LocalDateTime.now();
            }
            int size = 30 - this.stepsForLastDays.size();
            for (int i3 = 0; i3 < size; i3++) {
                localDateTime = localDateTime.minusDays(1);
                this.stepsForLastDays.add(Step.builder().id(DUMMY_ID).date(localDateTime).steps(0.0f).offset(0.0f).build());
            }
        }
        setAverageLabels();
        for (int i4 = 0; i4 < this.stepsForLastDays.size(); i4++) {
            this.entries.add(new BarEntry(i4, this.stepsForLastDays.get(i4).getSteps()));
        }
        BarDataSet barDataSet = new BarDataSet(this.entries, "");
        this.dataSet = barDataSet;
        this.data = new BarData(barDataSet);
        Legend legend = this.chart.getLegend();
        Description description = this.chart.getDescription();
        description.setText("");
        legend.setEnabled(false);
        LimitLine limitLine = new LimitLine(this.userPreferences.stepTarget().get().intValue());
        limitLine.enableDashedLine(10.0f, 10.0f, 0.0f);
        limitLine.setTextColor(getResources().getColor(R.color.white));
        limitLine.setLineColor(getResources().getColor(R.color.white));
        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        limitLine.setLineWidth(1.0f);
        YAxis axisLeft = this.chart.getAxisLeft();
        axisLeft.addLimitLine(limitLine);
        axisLeft.setDrawLimitLinesBehindData(false);
        YAxis axisRight = this.chart.getAxisRight();
        XAxis xAxis = this.chart.getXAxis();
        this.dataSet.setColor(AttributeUtils.getAttributeValue(getContext(), R.attr.colorPrimary));
        this.dataSet.setValueTextColor(getResources().getColor(R.color.grayDark));
        this.dataSet.setDrawValues(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12.0f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(12.0f);
        xAxis.setTextColor(AttributeUtils.getAttributeValue(getContext(), R.attr.customTextColor));
        this.chart.getAxisLeft().setAxisMinimum(1.0f);
        this.chart.getAxisRight().setAxisMinimum(1.0f);
        this.chart.setHorizontalScrollBarEnabled(true);
        axisLeft.setTextSize(12.0f);
        axisLeft.setDrawLabels(true);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(true);
        axisLeft.setGridColor(AttributeUtils.getAttributeValue(getContext(), R.attr.customTextColor));
        axisLeft.setTextColor(AttributeUtils.getAttributeValue(getContext(), R.attr.customTextColor));
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);
        xAxis.setValueFormatter(new ValueFormatter() { // from class: com.kewitschka.stepcounter.ui.stats.StatsFragment.2
            @Override // com.github.mikephil.charting.formatter.ValueFormatter
            public String getFormattedValue(float f) {
                int i5 = (int) f;
                return i5 < BaseStatsFragment.this.stepsForLastDays.size() ? (i > 7 || i5 >= 7) ? BaseStatsFragment.this.dateAndTimeUtils.timestampToDate(((Step) BaseStatsFragment.this.stepsForLastDays.get(i5)).getDate().toDate()) : BaseStatsFragment.this.formatHelper.dateToWeekday(((Step) BaseStatsFragment.this.stepsForLastDays.get(i5)).getDate()) : "";
            }
        });
        this.data.setBarWidth(i <= 7 ? 0.5f : 0.8f);
        this.data.setValueTextSize(12.0f);
        this.dataSet.setHighLightColor(0);
        this.dataSet.setHighLightAlpha(0);
        this.chart.setData(this.data);
        this.chart.setFitBars(false);
        this.chart.setScaleEnabled(false);
        this.chart.setTouchEnabled(true);
        this.chart.setDoubleTapToZoomEnabled(false);
        this.chart.animateY(400);
        this.chart.setDrawBorders(false);
        this.chart.setDescription(description);
        this.chart.setDrawValueAboveBar(false);
        this.chart.setBackgroundColor(AttributeUtils.getAttributeValue(getContext(), R.attr.customCardBackground));
        BarChart barChart = this.chart;
        RoundedBarChartRenderer roundedBarChartRenderer = new RoundedBarChartRenderer(barChart, barChart.getAnimator(), this.chart.getViewPortHandler());
        if (i > 7) {
            i2 = 15;
        }
        roundedBarChartRenderer.setRadius(i2);
        this.chart.setRenderer(roundedBarChartRenderer);
        BarChart barChart2 = this.chart;
        if (i > 7) {
            z = false;
        }
        barChart2.setAutoScaleMinMaxEnabled(z);
        this.chart.setVisibleXRangeMaximum(i);
        this.chart.setMarker(new MarkerTooltip(getContext(), R.layout.tooltip));
        this.chart.invalidate();
    }

    private void setAverageLabels() {
        float orElse = (float) Stream.of(this.stepsForLastDays.subList(0, this.days[this.allPreferences.statsDaysIndex().get().intValue()])).filter(StatFragmentPredicate.INSTANCE).mapToDouble(DoubleFun.INSTANCE).average().orElse(Utils.DOUBLE_EPSILON);
        this.averageSteps.setText(this.formatHelper.formatSteps(orElse));
        this.averageKm.setText(this.formatHelper.formatKm(orElse));
        this.averageMin.setText(this.formatHelper.formatMinutes(orElse));
        this.averageCalories.setText(this.formatHelper.formatCalories(orElse));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static  boolean lambda$setAverageLabels$0(Step step) {
        return !step.getId().equals(DUMMY_ID);
    }

    private void initTargetViews() {
        this.targetViewContainer.removeAllViews();
        List<Step> subList = this.stepsForLastDays.subList(0, 7);
        final int intValue = this.userPreferences.stepTarget().get().intValue();
        Stream.of(subList).forEach(new Consumer() { // from class: com.kewitschka.stepcounter.ui.stats.-$$Lambda$StatsFragment$HJXDR38I4-9wwZmKdLT_xom_Ia4
            @Override // com.annimon.stream.function.Consumer
            public final void accept(Object obj) {
                BaseStatsFragment.this.lambda$initTargetViews$1$StatsFragment(intValue, (Step) obj);
            }
        });
    }

    public  void lambda$initTargetViews$1$StatsFragment(int i, Step step) {
        TargetView targetView = new TargetView(getContext(), this.formatHelper.dateToWeekday(step.getDate()), i, step.getSteps(), step.getDate());
        targetView.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0f));
        this.targetViewContainer.addView(targetView);
    }

    public void updateToday() {
        Step stepToday = this.databaseRepository.stepToday();
        Step step = this.stepsForLastDays.get(0);
        if (stepToday.getDate() != null && step != null && step.getDate() != null) {
            if (isSameDay(stepToday.getDate(), step.getDate()) && stepToday.getSteps() != step.getSteps()) {
                ((BarEntry) this.dataSet.getEntryForIndex(0)).setY(stepToday.getSteps());
                this.chart.notifyDataSetChanged();
                this.chart.invalidate();
                if (this.targetViewContainer.getChildCount() > 0) {
                    ((TargetView) this.targetViewContainer.getChildAt(0)).setSteps(stepToday.getSteps());
                }
            } else if (!isSameDay(stepToday.getDate(), step.getDate())) {
                showLineChart(this.days[this.allPreferences.statsDaysIndex().get().intValue()]);
                initTargetViews();
            }
        }
    }

    private boolean isSameDay(LocalDateTime localDateTime, LocalDateTime localDateTime2) {
        return localDateTime.toLocalDate().equals(localDateTime2.toLocalDate());
    }

    private void insertTestData(int i) {
        LocalDateTime now = LocalDateTime.now();
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            this.databaseRepository.insertOrUpdateStep(Step.builder().id(String.format(Locale.getDefault(), "steps_%d_%d_%d", Integer.valueOf(now.dayOfMonth().get()), Integer.valueOf(now.monthOfYear().get()), Integer.valueOf(now.year().get()))).steps(random.nextInt(15000)).date(now).offset(1.0f).build());
            now = now.minusDays(1);
        }
    }
}
