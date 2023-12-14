package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication6.Data.Singelton.AllData;
import com.github.mikephil.charting.data.BarData;

public class CategoryDetails extends AppCompatActivity {

    private AllData allData;
    private int indexOfCategory = 0;

    public void setIndexOfCategory(int indexOfCategory) {
        this.indexOfCategory = indexOfCategory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        allData = AllData.getInstance();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 594
        );

        indexOfCategory = allData.getCategories().get(allData.getSelectedCategoryIndex()).getId()-1;

        TextView textView = new TextView(this);
        textView.setText("" + allData.getCategories().get(indexOfCategory).getName());
        textView.setTextSize(24); // Textgröße ändern
        textView.setTextColor(Color.BLACK); // Textfarbe ändern
        textView.setPadding(8, 8, 8, 8); // Innenabstand setzen
        textView.setTypeface(null, Typeface.BOLD); // Fettstil setzen
        textView.setGravity(Gravity.CENTER); // Zentrierung horizontal und vertikal
        textView.setBackgroundColor(Color.LTGRAY); // Hintergrundfarbe setzen

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textParams.setMargins(0, 16, 0, 0); // Setze Abstand oben
        textView.setLayoutParams(textParams);

        LinearLayout parentLayout = findViewById(R.id.parentLayout);
        parentLayout.addView(textView);

        generateAllExpenses();
    }

    public void generateAllExpenses(){

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout parentLayout = findViewById(R.id.parentLayout);


        for (int i = 0; i < allData.getCategories().get(indexOfCategory).getExpenses().size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(allData.getCategories().get(indexOfCategory).getExpenses().get(i).toString());
            parentLayout.addView(textView);
            textView.setLayoutParams(textParams);
        }
    }
}