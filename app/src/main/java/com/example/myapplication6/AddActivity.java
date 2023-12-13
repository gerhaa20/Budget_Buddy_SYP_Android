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

import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Expense;
import com.example.myapplication6.Data.Singelton.AllData;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddActivity extends AppCompatActivity {


    private LinearLayout layoutExpense;
    private LinearLayout layoutCategory;
    private RadioButton radioButtonExpense;
    private RadioButton radioButtonCategory;
    private Button buttonSaveExpense;
    private Button buttonSaveCategory;
    private Button btnGoBack;
    private EditText editTextAmount;

    private EditText editTextCategoryName;

    private AllData data;

    private EditText editTextName;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        data = AllData.getInstance();

        layoutExpense = findViewById(R.id.layoutExpense);
        layoutCategory = findViewById(R.id.layoutCategory);

        radioButtonExpense = findViewById(R.id.radioButtonExpense);
        radioButtonCategory = findViewById(R.id.radioButtonCategory);

        buttonSaveExpense = findViewById(R.id.buttonSaveExpense);
        buttonSaveCategory = findViewById(R.id.buttonSaveCategory);

        editTextCategoryName = findViewById(R.id.editTextCategoryName);

        editTextAmount = findViewById(R.id.editTextAmount);

        editTextName = findViewById(R.id.editTextName);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button buttonGoBack = findViewById(R.id.buttonGoBack);

        Spinner categorySpinner = findViewById(R.id.categorySpinner);

        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data.getCategories());
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
            String selectedCategory = categorySpinner.getSelectedItem().toString();
            double expenseAmount = Double.parseDouble(editTextAmount.getText().toString());

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            String currentDate = sdf.format(new Date());

            Expense expense = new Expense((float) expenseAmount, currentDate, editTextName.getText().toString());
            //System.out.println("selected Category[" + categoryIdx + "]: " + selectedCategory);
            data.getCategories().get(data.searchForCategory(selectedCategory)).addExpense(expense);

            /* Check
            for (Category category : data.getCategories()) {
                System.out.println("idx: " + categoryIdx);
                System.out.println("Name: " + category.getName());
                System.out.println("Expenses: " + category.getExpenses());
            }*/
        });

        buttonSaveCategory.setOnClickListener(view -> {
            String enteredText = editTextCategoryName.getText().toString();
            System.out.println(enteredText);
            if (!enteredText.isEmpty()) {
                data.getCategories().add(new Category(enteredText));

                // Check
                // System.out.println("Gespeicherte Kategorien: " + data.getCategories().toString());
            }
        });

        buttonGoBack.setOnClickListener(view -> {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}