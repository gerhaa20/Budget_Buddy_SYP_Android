package com.example.myapplication6.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication6.Add.AddSavingPlan;
import com.example.myapplication6.BarCode;
import com.example.myapplication6.Data.Savingplan;
import com.example.myapplication6.Data.Singelton.AllData;
import com.example.myapplication6.MainActivity;
import com.example.myapplication6.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class SavingPlanActivity extends AppCompatActivity {
    Button addSaving;
    AllData data;
    // Barcode (delete later)
    Button barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_plan);


        data = AllData.getInstance();

        addSaving = findViewById(R.id.btnAddSaving);
        // Barcode (delete later)
        barcode = findViewById(R.id.btnBarCode);
        addSaving.setOnClickListener(view -> openAddSavings());
        openDiagrams();

        Button buttonGoBack = findViewById(R.id.buttonGoBack);

        buttonGoBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        //barcode (delete later)
        barcode.setOnClickListener(v -> {
            Intent intent = new Intent(this, BarCode.class);
            startActivity(intent);
            finish();
        });
    }

    public void openDiagrams(){
        int length = data.getSavingplans().size();
        List<Savingplan> allSavingplans;
        allSavingplans = data.getSavingplans();

        for (int i = 0; i < length; i++) {
            generateBarChart(allSavingplans.get(i));
        }
    }

    public void generateBarChart(Savingplan savingplan) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        float totalAmount = 0f;
        for (int i = 0; i < savingplan.getSavings().size(); i++) {
            float value = savingplan.getSavings().get(i).getAmount();
            totalAmount += value;
        }

        float remainingAmount = savingplan.getGoal() - totalAmount;

        pieEntries.add(new PieEntry(totalAmount, "Gespart"));

        if (remainingAmount > 0) {
            pieEntries.add(new PieEntry(remainingAmount, "Fehlt"));
        }

        int greenColor = Color.parseColor("#00FF00");
        int redColor = Color.parseColor("#FF0000");

        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<Integer> valueColors = new ArrayList<>();

        for (int i = 0; i < pieEntries.size(); i++) {
            if (i < pieEntries.size() - 1) {
                colors.add(greenColor);
                valueColors.add(greenColor);
            } else {
                colors.add(redColor);
                valueColors.add(redColor);
            }
        }

        PieChart pieChart = new PieChart(this);
        pieChart.setId(savingplan.getId());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 594
        );

        pieChart.setLayoutParams(layoutParams);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(colors);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueLinePart1OffsetPercentage(10);
        pieDataSet.setValueLinePart1Length(0.6f);
        pieDataSet.setValueLinePart2Length(0.5f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieData.setValueTextSize(13f);
        pieData.setValueTextColor(Color.BLACK);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);

        pieChart.animateY(1500);
        pieChart.getDescription().setText("");

        TextView textView = new TextView(this);
        textView.setText("" + savingplan.getName());
        textView.setTextSize(24);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(8, 8, 8, 8);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.LTGRAY);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        pieChart.setOnClickListener(view -> {
            data.setSelectedView(view.getId());
        });

        textParams.setMargins(0, 16, 0, 0);
        textView.setLayoutParams(textParams);

        LinearLayout parentLayout = findViewById(R.id.savingLayout);
        parentLayout.addView(textView);
        parentLayout.addView(pieChart);

        LinearLayout.LayoutParams hintParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        hintParams.setMargins(0, 16, 0, 0);
    }

    public void openAddSavings(){
        try {
            Intent intent = new Intent(this, AddSavingPlan.class);
            startActivity(intent);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}