package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication6.Data.Categorie;
import com.example.myapplication6.Data.Expenses;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {


    private LinearLayout layoutExpense;
    private LinearLayout layoutCategory;
    private RadioButton radioButtonExpense;
    private RadioButton radioButtonCategory;
    private Button buttonSaveExpense;
    private Button buttonSaveCategory;
    private Button btnGoBack;
    private EditText editTextAmount;

    private EditText editTextCategoryName;



    // Globale ArrayList für die gespeicherten Kategorien
    private List<Expenses> savedExpenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        layoutExpense = findViewById(R.id.layoutExpense);
        layoutCategory = findViewById(R.id.layoutCategory);

        radioButtonExpense = findViewById(R.id.radioButtonExpense);
        radioButtonCategory = findViewById(R.id.radioButtonCategory);

        buttonSaveExpense = findViewById(R.id.buttonSaveExpense);
        buttonSaveCategory = findViewById(R.id.buttonSaveCategory);

        editTextCategoryName = findViewById(R.id.editTextCategoryName);

        editTextAmount = findViewById(R.id.editTextAmount);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button buttonGoBack = findViewById(R.id.buttonGoBack);

        // Spinner für die Kategorien
        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        List<Categorie> categories = new ArrayList<>();
        categories.add(new Categorie("Freizeit"));
        categories.add(new Categorie("Essen & Trinken"));
        categories.add(new Categorie("Videospiele"));

        ArrayAdapter<Categorie> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        radioButtonExpense.setOnClickListener(view -> {
            layoutExpense.setVisibility(View.VISIBLE);
            layoutCategory.setVisibility(View.GONE);
        });

        radioButtonCategory.setOnClickListener(view -> {
            layoutCategory.setVisibility(View.VISIBLE);
            layoutExpense.setVisibility(View.GONE);
        });

        buttonSaveExpense.setOnClickListener(view -> {
            // Code zum Speichern der Ausgaben
            String selectedCategory = categorySpinner.getSelectedItem().toString();
            // You may need to get the expense amount from an EditText or another view
            double expenseAmount = Double.parseDouble(editTextAmount.getText().toString());

            Expenses expense = new Expenses((float) expenseAmount, selectedCategory);
            savedExpenses.add(expense);

            // Optional: Ausgabe der gespeicherten Ausgaben zur Überprüfung
            System.out.println("Gespeicherte Ausgaben: " + savedExpenses.toString());
        });

        buttonSaveCategory.setOnClickListener(view -> {
            // Code zum Speichern der Kategorie
            String enteredText = editTextCategoryName.getText().toString();
            System.out.println(enteredText);
            if (!enteredText.isEmpty()) {
                categories.add(new Categorie(enteredText));

                // Optional: Ausgabe der gespeicherten Kategorien zur Überprüfung
                System.out.println("Gespeicherte Kategorien: " + categories.toString());
            }
        });

        buttonGoBack.setOnClickListener(view -> {
            Intent intent = new Intent(Activity2.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}