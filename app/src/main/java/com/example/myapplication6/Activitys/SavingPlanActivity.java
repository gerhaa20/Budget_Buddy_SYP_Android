package com.example.myapplication6.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication6.Add.AddSavingPlan;
import com.example.myapplication6.R;

public class SavingPlanActivity extends AppCompatActivity {
    Button addSaving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_plan);

        addSaving = findViewById(R.id.btnAddSaving);
        addSaving.setOnClickListener(view -> openAddSavings());
    }

    public void openAddSavings(){
        try {
            Intent intent = new Intent(this, AddSavingPlan.class);
            startActivity(intent);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}