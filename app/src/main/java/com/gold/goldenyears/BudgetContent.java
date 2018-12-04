package com.gold.goldenyears;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.PieData;

import java.util.ArrayList;


public class BudgetContent extends Fragment{

    public View end;

    float h;
    float v;
    float m;
    float d;
    float f;

    float[] yData = new float[5];

    public static String TAG = "Pie";

    public String[] xData = {"Housing","Vehicle","Food","Miscellaneous","Discretionary"};

    PieChart pieChart;
    public BudgetContent() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        end = inflater.inflate(R.layout.fragment_budget_content, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        h = Float.parseFloat(sharedPreferences.getString("user_housing", "0"));
        m = Float.parseFloat(sharedPreferences.getString("user_misc", "0"));
        v =  Float.parseFloat(sharedPreferences.getString("user_vehicle", "0"));
        d = Float.parseFloat(sharedPreferences.getString("user_disc", "0"));
        f = Float.parseFloat(sharedPreferences.getString("user_food", "0"));
        yData[0] = h;
        yData[1] = v;
        yData[2] = f;
        yData[3] = m;
        yData[4] = d;

        pieChart = (PieChart) end.findViewById(R.id.idPieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(20);
        pieChart.setCenterText("Budget");
        pieChart.setDrawEntryLabels(true);
        pieChart.setTransparentCircleAlpha(0);
        addDataSet();
        return end;
    }

    public void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i]));
        }

        for (int i = 0; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Budget Summary");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend leg = pieChart.getLegend();
        leg.setForm(Legend.LegendForm.CIRCLE);
        leg.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
