package com.example.myapplication6.Add;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.myapplication6.Activitys.SavingPlanActivity;
import com.example.myapplication6.Data.Saving;
import com.example.myapplication6.Data.Savingplan;
import com.example.myapplication6.Data.Singelton.AllData;
import com.example.myapplication6.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddSavingPlan extends AppCompatActivity {
    private AllData data;
    private LinearLayout layoutSavingPlan;
    private LinearLayout layoutSaving;
    private RadioButton radioButtonSavingplan;
    private RadioButton radioButtonSaving;
    private EditText editTextSpavingplanName;
    private EditText editTextSavingplanAmount;
    private EditText editTextSavingAmount;
    private Button buttonSaveSavingplan;
    private Button buttonSaveSaving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saving_plan);

        data = AllData.getInstance();

        layoutSavingPlan = findViewById(R.id.layoutSavingPlan);
        layoutSaving = findViewById(R.id.layoutSaving);

        radioButtonSavingplan = findViewById(R.id.radioButtonSavingplan);
        radioButtonSaving = findViewById(R.id.radioButtonSaving);

        buttonSaveSavingplan = findViewById(R.id.buttonSaveSavingplan);
        buttonSaveSaving = findViewById(R.id.buttonSaveSaving);
        Button buttonGoBack = findViewById(R.id.buttonGoBack);

        editTextSpavingplanName = findViewById(R.id.editTextSavingplanName);
        editTextSavingplanAmount = findViewById(R.id.editTextSavingplanAmount);
        editTextSavingAmount = findViewById(R.id.editTextSavingAmount);

        Spinner savingPlanSpinner = findViewById(R.id.savingPlanSpinner);

        ArrayAdapter<Savingplan> savingPlanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data.getSavingplans());
        savingPlanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        savingPlanSpinner.setAdapter(savingPlanAdapter);

        radioButtonSavingplan.setOnClickListener(view -> {
            layoutSaving.setVisibility(View.GONE);
            layoutSavingPlan.setVisibility(View.VISIBLE);
        });

        radioButtonSaving.setOnClickListener(view -> {
            layoutSavingPlan.setVisibility(View.GONE);
            layoutSaving.setVisibility(View.VISIBLE);
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
                // noch in Arbeit !!!!!!!!!!!!!!!!!!!!!!!!!!!
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

            System.out.println("Data: " + data.getSavingplans());

            for(Savingplan savingplan: data.getSavingplans()) {
                System.out.println("ID: " + savingplan.getId());
                System.out.println("Name: " + savingplan.getName());
                System.out.println("currentMoney: " + savingplan.getCurrentMoney());
                System.out.println("goal: " + savingplan.getGoal());
                System.out.println("savings: " + savingplan.getSavings());
                System.out.println("-------------------");
            }
        });

        buttonGoBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, SavingPlanActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private static boolean containsOnlyNumbers(String input) {
        return input.matches("\\d+");
    }
}