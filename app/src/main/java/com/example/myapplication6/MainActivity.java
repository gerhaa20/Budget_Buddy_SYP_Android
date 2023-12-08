package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication6.Data.Categorie;
import com.example.myapplication6.Data.Expenses;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BarChart barChart;
    BarChart barChart2;
    TextView text1;
    TextView text2;

    Button addCategoryOrExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barChart = findViewById(R.id.bar_chart);
        barChart2 = findViewById(R.id.bar_chart2);
        text1 = findViewById(R.id.kategorieText1);
        text2 = findViewById(R.id.kategorieText2);
        addCategoryOrExpense = findViewById(R.id.btnAddCatEx);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        Categorie categorie = new Categorie("food");
        categorie.addExpense(new Expenses(5.4f,"1"));
        categorie.addExpense(new Expenses(10.4f,"1"));
        categorie.addExpense(new Expenses(15.4f,"1"));
        categorie.addExpense(new Expenses(20.4f,"1"));
        categorie.addExpense(new Expenses(30.4f,"1"));
        categorie.addExpense(new Expenses(35.4f,"1"));
        categorie.addExpense(new Expenses(55.4f,"1"));

        text1.setText(categorie.getName());
        text2.setText(categorie.getName());

        for (int i = 0; i < categorie.getExpenses().size(); i++) {
            float value = categorie.getExpenses().get(i).getAmount();
            BarEntry barEntry = new BarEntry(i,value);
            barEntries.add(barEntry);
        }

        //Initialize bar data
        BarDataSet barDataSet = new BarDataSet(barEntries,"Expenses");

        //Set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //Hide draw Value
        barDataSet.setDrawValues(false);

        //Set Bar Data
        barChart.setData(new BarData(barDataSet));
        barChart2.setData(new BarData(barDataSet));

        // Animation
        barChart.animateY(2500);
        barChart2.animateY(2500);

        // Set description text
        barChart.getDescription().setText("last years expenses");
        barChart.getDescription().setTextColor(Color.BLUE);
        barChart2.getDescription().setText("last years expenses");
        barChart2.getDescription().setTextColor(Color.BLUE);

        addCategoryOrExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Setze das Layout der Aktivität auf eine andere XML-Datei
                //setContentView(R.layout.add_categories);
                //Intent intent = new Intent(MainActivity.this, AddActivities.class);
                //startActivity(intent); // Starte die neue Aktivität*/
                openSecondActivity();
            }
        });
    }

    public void openSecondActivity(){
        try {
            Intent intent = new Intent(this, Activity2.class);
            startActivity(intent);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }


}