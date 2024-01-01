package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Singelton.AllData;

public class CategoryDetails extends AppCompatActivity {
    private AllData allData;
    private int indexOfCategory;
    private Category selectedCategory;
    private Button buttonGoBack;
    private ImageButton imgBtnDeleteCategory;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        allData = AllData.getInstance();

        LinearLayout parentLayout = findViewById(R.id.parentLayout);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        imgBtnDeleteCategory = findViewById(R.id.imgBtnDeleteCategory);
        indexOfCategory = allData.getSelectedCategoryID(allData.getSelectedView());
        selectedCategory = allData.getCategories().get(indexOfCategory);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 594
        );

        if (indexOfCategory >= 0 && indexOfCategory < allData.getCategories().size()) {
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
            parentLayout.addView(textView);
            generateAllExpenses();
        }

        buttonGoBack.setOnClickListener(view -> {
            saveAndClose();
        });

        imgBtnDeleteCategory.setOnClickListener(view -> {
            allData.deleteCategory(selectedCategory);
            saveAndClose();
        });
    }

    public void generateAllExpenses(){
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout parentLayout = findViewById(R.id.parentLayout);

        for (int i = 0; i < allData.getCategories().get(indexOfCategory).getExpenses().size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(selectedCategory.getExpenses().get(i).toString());
            parentLayout.addView(textView);
            textView.setLayoutParams(textParams);
        }
    }

    public void saveAndClose(){
        Intent intent = new Intent(CategoryDetails.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}