package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Expense;
import com.example.myapplication6.Data.Singelton.AllData;

public class CategoryDetails extends AppCompatActivity {
    private AllData allData;
    private int indexOfCategory;
    private Category selectedCategory;
    private Button buttonGoBack;
    private ImageButton imgBtnDeleteCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        allData = AllData.getInstance();

        buttonGoBack = findViewById(R.id.buttonGoBack);
        imgBtnDeleteCategory = findViewById(R.id.imgBtnDeleteCategory);
        indexOfCategory = allData.getSelectedCategoryID(allData.getSelectedView());
        selectedCategory = allData.getCategories().get(indexOfCategory);

        TextView textViewCategoryName = findViewById(R.id.textViewCategoryName);
        textViewCategoryName.setText(selectedCategory.getName());

        if (indexOfCategory >= 0 && indexOfCategory < allData.getCategories().size()) {
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

    public void generateAllExpenses() {
        TableLayout tableLayout = findViewById(R.id.parentLayout);

        TableRow tableHeader = new TableRow(this);
        tableHeader.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        String[] headers = {"Name", "Amount", "Date"};

        for (String header : headers) {
            TextView headerView = new TextView(this);
            headerView.setText(header);
            headerView.setPadding(8, 8, 8, 8);
            headerView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            headerView.setBackgroundColor(Color.LTGRAY);
            tableHeader.addView(headerView);
        }

        View headerBorder = new View(this);
        headerBorder.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
        headerBorder.setBackgroundColor(Color.BLACK);
        tableHeader.addView(headerBorder);

        TableRow.LayoutParams headerLayoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        headerLayoutParams.bottomMargin = 4;
        tableHeader.setLayoutParams(headerLayoutParams);
        tableLayout.addView(tableHeader);

        for (Expense expense : selectedCategory.getExpenses()) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT));

            String[] columns = {expense.getName(), String.valueOf(expense.getAmount()), expense.getDate()};

            for (String column : columns) {
                TextView textView = new TextView(this);
                textView.setText(column);
                textView.setPadding(8, 8, 8, 8);
                textView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                textView.setBackgroundColor(Color.WHITE);
                textView.setMinimumWidth(350);


                textView.setOnClickListener(v -> {
                    allData.setSelectedExpense(selectedCategory.getExpenses().get(expense.getId()-1));
                    openExpenseDetails();
                });

                tableRow.addView(textView);
            }

            View rowBorder = new View(this);
            rowBorder.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
            rowBorder.setBackgroundColor(Color.BLACK);
            tableRow.addView(rowBorder);

            TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            rowLayoutParams.bottomMargin = 4;
            tableRow.setLayoutParams(rowLayoutParams);

            tableLayout.addView(tableRow);
        }
    }

    public void saveAndClose(){
        Intent intent = new Intent(CategoryDetails.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void openExpenseDetails(){
        try {
            Intent intent = new Intent(this, ExpenseDetailsActivity.class);
            startActivity(intent);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}