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
    private EditText editTextAmount;
    private EditText editTextCategoryName;
    private AllData data;
    private EditText editTextName;
    private Category selectedCategory;

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
            String nameInput = editTextName.getText().toString().trim();
            String amountInput = editTextAmount.getText().toString().trim();

            if (containsOnlyNumbers(nameInput) || nameInput.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter a valid String", Toast.LENGTH_SHORT).show();
            } else if(amountInput.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
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

            for (Category category : data.getCategories()) {
                System.out.println("Name: " + category.getName());
                System.out.println("Expenses: " + category.getExpenses());
            }
        });

        buttonSaveCategory.setOnClickListener(view -> {
            String enteredText = editTextCategoryName.getText().toString().trim();

            if(containsOnlyNumbers(enteredText) || enteredText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please enter a valid String", Toast.LENGTH_SHORT).show();
            }else{
                data.getCategories().add(new Category(enteredText, data.getNextIdCategory()));
            }

            editTextCategoryName.setText("");

            for (Category category : data.getCategories()) {
                System.out.println("Index: " + (category.getId()-1));
                System.out.println("ID: "+ category.getId());
                System.out.println("Name: " + category.getName());
                System.out.println("Expenses: " + category.getExpenses());
                System.out.println("------------------------");
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