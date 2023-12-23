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
import android.widget.Spinner;
import android.widget.Toast;
import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Expense;
import com.example.myapplication6.Data.Saving;
import com.example.myapplication6.Data.Savingplan;
import com.example.myapplication6.Data.Singelton.AllData;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddActivity extends AppCompatActivity {
    private LinearLayout layoutExpense;
    private LinearLayout layoutCategory;
    private LinearLayout layoutSavingPlan;
    private LinearLayout layoutSaving;
    private RadioButton radioButtonExpense;
    private RadioButton radioButtonCategory;
    private RadioButton radioButtonSavingplan;
    private RadioButton radioButtonSaving;
    private Button buttonSaveExpense;
    private Button buttonSaveCategory;
    private Button buttonSaveSavingplan;
    private Button buttonSaveSaving;
    private EditText editTextAmount;
    private EditText editTextCategoryName;
    private EditText editTextSpavingplanName;
    private EditText editTextSavingplanAmount;
    private EditText editTextSavingAmount;
    private AllData data;
    private EditText editTextName;
    private Category selectedCategory;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        data = AllData.getInstance();

        layoutExpense = findViewById(R.id.layoutExpense);
        layoutCategory = findViewById(R.id.layoutCategory);
        layoutSavingPlan = findViewById(R.id.layoutSavingPlan);
        layoutSaving = findViewById(R.id.layoutSaving);

        radioButtonExpense = findViewById(R.id.radioButtonExpense);
        radioButtonCategory = findViewById(R.id.radioButtonCategory);
        radioButtonSavingplan = findViewById(R.id.radioButtonSavingplan);
        radioButtonSaving = findViewById(R.id.radioButtonSaving);

        buttonSaveExpense = findViewById(R.id.buttonSaveExpense);
        buttonSaveCategory = findViewById(R.id.buttonSaveCategory);
        buttonSaveSavingplan = findViewById(R.id.buttonSaveSavingplan);
        buttonSaveSaving = findViewById(R.id.buttonSaveSaving);
        Button buttonGoBack = findViewById(R.id.buttonGoBack);

        editTextCategoryName = findViewById(R.id.editTextCategoryName);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextName = findViewById(R.id.editTextName);
        editTextSpavingplanName = findViewById(R.id.editTextSavingplanName);
        editTextSavingplanAmount = findViewById(R.id.editTextSavingplanAmount);
        editTextSavingAmount = findViewById(R.id.editTextSavingAmount);

        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        Spinner savingPlanSpinner = findViewById(R.id.savingPlanSpinner);

        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data.getCategories());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        ArrayAdapter<Savingplan> savingPlanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data.getSavingplans());
        savingPlanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        savingPlanSpinner.setAdapter(savingPlanAdapter);

        radioButtonExpense.setOnClickListener(view -> {
            layoutExpense.setVisibility(View.VISIBLE);
            layoutCategory.setVisibility(View.GONE);
            layoutSaving.setVisibility(View.GONE);
            layoutSavingPlan.setVisibility(View.GONE);
        });

        radioButtonCategory.setOnClickListener(view -> {
            layoutCategory.setVisibility(View.VISIBLE);
            layoutExpense.setVisibility(View.GONE);
            layoutSaving.setVisibility(View.GONE);
            layoutSavingPlan.setVisibility(View.GONE);
        });

        radioButtonSavingplan.setOnClickListener(view -> {
            layoutExpense.setVisibility(View.GONE);
            layoutCategory.setVisibility(View.GONE);
            layoutSaving.setVisibility(View.GONE);
            layoutSavingPlan.setVisibility(View.VISIBLE);
        });

        radioButtonSaving.setOnClickListener(view -> {
            layoutExpense.setVisibility(View.GONE);
            layoutCategory.setVisibility(View.GONE);
            layoutSavingPlan.setVisibility(View.GONE);
            layoutSaving.setVisibility(View.VISIBLE);
        });

        buttonSaveExpense.setOnClickListener(view -> {
            String nameInput = editTextName.getText().toString().trim();
            String amountInput = editTextAmount.getText().toString().trim();

            if (containsOnlyNumbers(nameInput)) {
                Toast.makeText(getApplicationContext(), "Please enter a valid String", Toast.LENGTH_SHORT).show();
            } else if(amountInput.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
            } else if(nameInput.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please enter a valid String", Toast.LENGTH_SHORT).show();
            } else if (nameInput.contains("\\n")) {
                Toast.makeText(getApplicationContext(), "Amount cannot contain a newline character", Toast.LENGTH_SHORT).show();
            } else {
                selectedCategory = (Category) categorySpinner.getSelectedItem();
                double expenseAmount = Double.parseDouble(editTextAmount.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String currentDate = sdf.format(new Date());
                Expense expense = new Expense((float) expenseAmount, currentDate, nameInput, data.getNextIdExpense(selectedCategory));
                data.getCategories().get(data.searchForCategoryIndex(selectedCategory.getName())).addExpense(expense);

                editTextAmount.setText("");
                editTextName.setText("");
            }
        });

        buttonSaveCategory.setOnClickListener(view -> {
            String enteredText = editTextCategoryName.getText().toString().trim();

            if(containsOnlyNumbers(enteredText) || enteredText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please enter a valid String", Toast.LENGTH_SHORT).show();
            } else if (enteredText.contains("\\n")) {
                Toast.makeText(getApplicationContext(), "Amount cannot contain a newline character", Toast.LENGTH_SHORT).show();
            } else{
                data.getCategories().add(new Category(enteredText, data.getNextIdCategory()));
            }

            editTextCategoryName.setText("");
        });

        buttonSaveSavingplan.setOnClickListener(view -> {
            String nameInput = editTextSpavingplanName.getText().toString().trim();
            String amountInput = editTextSavingplanAmount.getText().toString().trim();

            if (containsOnlyNumbers(nameInput) || nameInput.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter a valid String", Toast.LENGTH_SHORT).show();
            } else if(amountInput.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
            } else if (nameInput.contains("\\n")) {
                Toast.makeText(getApplicationContext(), "Amount cannot contain a newline character", Toast.LENGTH_SHORT).show();
            } else {
                float expenseAmount = Float.parseFloat(editTextSavingplanAmount.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                data.getSavingplans().add(new Savingplan(data.getNextIdSavingplan(), expenseAmount, nameInput));

                editTextSpavingplanName.setText("");
                editTextSavingplanAmount.setText("");
            }
        });

        buttonSaveSaving.setOnClickListener(view -> {
            String amountInput = editTextSavingAmount.getText().toString().trim();

            if(amountInput.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
            } else if(amountInput.matches("0+")){
                Toast.makeText(getApplicationContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            } else {
                float expenseAmount = Float.parseFloat(editTextSavingAmount.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String currentDate = sdf.format(new Date());
                Savingplan selectedSavingPlan = (Savingplan) savingPlanSpinner.getSelectedItem();

                Saving saving = new Saving(data.getNextIdSaving(selectedSavingPlan),expenseAmount,currentDate);
                data.getSavingplans().get(data.searchForSavingplanIndex(selectedSavingPlan.getName())).addSaving(saving);

                editTextSavingAmount.setText("");
            }
        });

        buttonGoBack.setOnClickListener(view -> {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private static boolean containsOnlyNumbers(String input) {
        return input.matches("\\d+");
    }
}