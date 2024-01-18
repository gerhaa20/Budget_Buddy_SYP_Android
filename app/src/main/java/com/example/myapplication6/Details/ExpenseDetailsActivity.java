package com.example.myapplication6.Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication6.Data.Expense;
import com.example.myapplication6.Data.Singelton.AllData;
import com.example.myapplication6.MainActivity;
import com.example.myapplication6.R;

public class ExpenseDetailsActivity extends AppCompatActivity {

    private AllData allData;
    private EditText editTextAmount;
    private EditText editTextName;
    private Button btnUpdateExpense;
    private ImageButton imgBtnDeleteExpense;
    private Expense selectedExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        allData = AllData.getInstance();

        selectedExpense = allData.getSelectedExpense();
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextName = findViewById(R.id.editTextName);
        btnUpdateExpense = findViewById(R.id.btnUpdateExpense);
        imgBtnDeleteExpense = findViewById(R.id.imgBtnDeleteExpense);

        editTextName.setText(selectedExpense.getName());
        editTextAmount.setText(String.valueOf(selectedExpense.getAmount()));

        btnUpdateExpense.setOnClickListener(view -> saveAndClose());

        imgBtnDeleteExpense.setOnClickListener(view -> {
            allData.deleteExpense(selectedExpense);
            selectedExpense = new Expense(0,"0","",0);
            saveAndClose();
        });
    }

    public void saveAndClose(){
        String updatedName = editTextName.getText().toString();
        Float updatedAmount = Float.parseFloat(editTextAmount.getText().toString());

        allData.getSelectedExpense().setName(updatedName);
        allData.getSelectedExpense().setAmount(updatedAmount);

        Intent intent = new Intent(ExpenseDetailsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}