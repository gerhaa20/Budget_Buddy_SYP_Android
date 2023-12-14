package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Expense;
import com.example.myapplication6.Data.Singelton.AllData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addCategoryOrExpense;
    private AllData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCategoryOrExpense = findViewById(R.id.btnChangeView);
        data = AllData.getInstance();
        addCategoryOrExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
            }
        });
        generateDiagrams();
    }

    public void generateDiagrams(){
        int length = data.getCategories().size();
        List<Category> allCategories = new ArrayList<>();
        allCategories = data.getCategories();

        for (int i = 0; i < length; i++) {
            generateBarChart(allCategories.get(i),i);
        }
    }

    public void generateBarChart(Category category, int chartNumber) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < category.getExpenses().size(); i++) {
            float value = category.getExpenses().get(i).getAmount();
            BarEntry barEntry = new BarEntry(i, value);
            barEntries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Expenses");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(false);

        BarChart barChart = new BarChart(this);
        barChart.setId(View.generateViewId());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 594
        );

        barChart.setLayoutParams(layoutParams);

        barChart.setData(new BarData(barDataSet));
        barChart.animateY(1500);
        barChart.getDescription().setText(category.getName() + " expenses");
        barChart.getDescription().setTextColor(Color.BLUE);

        //---------------TextView über jedem BarChart --> ChatGpt-----------
        TextView textView = new TextView(this);
        textView.setText("" + category.getName());
        textView.setTextSize(24); // Textgröße ändern
        textView.setTextColor(Color.BLACK); // Textfarbe ändern
        textView.setPadding(8, 8, 8, 8); // Innenabstand setzen
        textView.setTypeface(null, Typeface.BOLD); // Fettstil setzen
        textView.setGravity(Gravity.CENTER); // Zentrierung horizontal und vertikal
        textView.setBackgroundColor(Color.LTGRAY); // Hintergrundfarbe setzen
        // -----------------------------------------------------------------

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textParams.setMargins(0, 16, 0, 0); // Setze Abstand oben
        textView.setLayoutParams(textParams);

        LinearLayout parentLayout = findViewById(R.id.parentLayout);
        parentLayout.addView(textView);
        parentLayout.addView(barChart);
    }

    public void openSecondActivity(){
        try {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}