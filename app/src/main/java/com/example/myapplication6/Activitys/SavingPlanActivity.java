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
    Button barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_plan);


        data = AllData.getInstance();
        barcode = findViewById(R.id.btnBarCode);
        addSaving = findViewById(R.id.btnAddSaving);
        addSaving.setOnClickListener(view -> openAddSavings());
        openDiagrams();

        Button buttonGoBack = findViewById(R.id.buttonGoBack);

        buttonGoBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        barcode.setOnClickListener(v -> {
            Intent intent = new Intent(this, BarCode.class);
            startActivity(intent);
            finish();
        });
    }

    public void openDiagrams(){
        int length = data.getSavingplans().size();
        List<Savingplan> allSavingplans = new ArrayList<>();
        allSavingplans = data.getSavingplans();

        for (int i = 0; i < length; i++) {
            generateBarChart(allSavingplans.get(i));
        }
    }

    public void generateBarChart(Savingplan savingplan) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        float sum = 0.0f;
        float goal = savingplan.getGoal();
        float percent_saved = 0.0f;
        float percent_to_goal = 0.0f;

        for (int i = 0; i < savingplan.getSavings().size(); i++) {

            sum += savingplan.getSavings().get(i).getAmount();

            /*float value = savingplan.getSavings().get(i).getAmount();
            PieEntry pieEntry = new PieEntry(i, value);
            pieEntries.add(pieEntry);*/
        }

        percent_saved = (sum * 100) / goal;
        percent_to_goal = 100 - percent_saved;

        pieEntries.add(new PieEntry(percent_saved));
        pieEntries.add(new PieEntry(percent_to_goal));

        System.out.println("##########################SAVING###############################: " + sum);

        //pieEntries.add(new PieEntry(1,50.0));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, " Savingplan");
        pieDataSet.setColors(Color.GREEN,Color.RED);
        pieDataSet.setDrawValues(false);


        PieChart pieChart = new PieChart(this);
        pieChart.setId(savingplan.getId());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 594
        );

        pieChart.setLayoutParams(layoutParams);

        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateY(1500);
        //pieChart.getDescription().setText(savingplan.getName() + " Savingplan");
        pieChart.getDescription().setTextColor(Color.WHITE);


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
            //openCategoryDetails();
        });

        textParams.setMargins(0, 16, 0, 0); // Setze Abstand oben
        textView.setLayoutParams(textParams);

        LinearLayout parentLayout = findViewById(R.id.savingLayout);
        parentLayout.addView(textView);
        parentLayout.addView(pieChart);
    }

    /*
    public void generateDiagrams(){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        pieEntries.add(new PieEntry(1,2));
        pieEntries.add(new PieEntry(3,4));
        pieEntries.add(new PieEntry(6,2));
        pieEntries.add(new PieEntry(2,1));

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Savings");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setDrawValues(false);

        PieChart pieChart = new PieChart(this);
        pieChart.setId(0);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 594
        );

        pieChart.setLayoutParams(layoutParams);

        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateY(1500);
        pieChart.getDescription().setText("Savings");
        pieChart.getDescription().setTextColor(Color.BLUE);

        LinearLayout parentLayout = findViewById(R.id.savingLayout);
        parentLayout.addView(pieChart);
    }*/

    public void openAddSavings(){
        try {
            Intent intent = new Intent(this, AddSavingPlan.class);
            startActivity(intent);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

}