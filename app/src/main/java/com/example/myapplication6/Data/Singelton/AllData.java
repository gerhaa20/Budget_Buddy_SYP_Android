package com.example.myapplication6.Data.Singelton;

import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Expense;

import java.util.ArrayList;
import java.util.List;

public class AllData {
    private static AllData instance = null;

    private List<Category> categories;

    private int selectedCategoryIndex;

    public AllData() {
        this.categories = new ArrayList<>();

        Category foodC = new Category("Food", 1);
        /*foodC.addExpense(new Expense(5.4f,"1","Starbucks"));
        foodC.addExpense(new Expense(5.4f,"1","Starbucks"));
        foodC.addExpense(new Expense(5.4f,"1","Starbucks"));
        foodC.addExpense(new Expense(5.4f,"1","Starbucks"));
        foodC.addExpense(new Expense(5.4f,"1","Starbucks"));*/
        categories.add(foodC);

        Category freetimeC = new Category("Freizeit", 2);
        /*freetimeC.addExpense(new Expense(5.4f,"1","FIF"));
        freetimeC.addExpense(new Expense(5.4f,"1","FIF"));
        freetimeC.addExpense(new Expense(5.4f,"1","FIF"));
        freetimeC.addExpense(new Expense(5.4f,"1","FIF"));
        freetimeC.addExpense(new Expense(5.4f,"1","FIF"));*/

        categories.add(freetimeC);
    }

    public static AllData getInstance(){
        if (instance == null){
            instance = new AllData();
        }
        return instance;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public int searchForCategoryIndex(String categoryName) {
        int index = 0;

        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getName().equals(categoryName)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public int getNextIdCategory(){
        return getCategories().size()+1;
    }

    public int getNextIdExpense(Category selectedCategory){
        return selectedCategory.getExpenses().size()+1;
    }

    public int getSelectedCategoryIndex() {
        return selectedCategoryIndex;
    }

    public void setSelectedCategoryIndex(int selectedCategoryIndex) {
        this.selectedCategoryIndex = selectedCategoryIndex;
    }
}
