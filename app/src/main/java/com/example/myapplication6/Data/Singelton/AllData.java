package com.example.myapplication6.Data.Singelton;

import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllData {
    private static AllData instance = null;

    private List<Category> categories;

    private int selectedCategoryID;

    private int selectedView;

    public AllData() {
        this.categories = new ArrayList<>();

        Category foodC = new Category("Food", 1);
        foodC.addExpense(new Expense(5.4f,"1","Starbucks",1));
        foodC.addExpense(new Expense(6.4f,"1","Starbucks",2));
        foodC.addExpense(new Expense(7.4f,"1","Starbucks",3));
        foodC.addExpense(new Expense(8.4f,"1","Starbucks",4));
        foodC.addExpense(new Expense(9.4f,"1","Starbucks",5));
        categories.add(foodC);

        Category freetimeC = new Category("Freizeit", 2);
        freetimeC.addExpense(new Expense(5.4f,"1","FIF",1));
        freetimeC.addExpense(new Expense(6.4f,"1","FIF",2));
        freetimeC.addExpense(new Expense(7.4f,"1","FIF",3));

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
        int maxId = 0;
        for (Category category : categories) {
            if (category.getId() > maxId) {
                maxId = category.getId();
            }
        }

        return maxId + 1;
    }

    public int getNextIdExpense(Category selectedCategory){
        return selectedCategory.getExpenses().size()+1;
    }

    public int getSelectedCategoryID(int id) {
        int reValue = 0;

        for(int i = 0; i < categories.size(); ++i){
            if(categories.get(i).getId() == id){
                reValue = i;
                break;
            }
        }

        return reValue;
    }

    public int getSelectedCategoryID() {
        return selectedCategoryID;
    }

    public void setSelectedCategoryID(int selectedCategoryID) {
        this.selectedCategoryID = selectedCategoryID;
    }

    public int getSelectedView() {
        return selectedView;
    }

    public void setSelectedView(int selectedView) {
        this.selectedView = selectedView;
    }
}
