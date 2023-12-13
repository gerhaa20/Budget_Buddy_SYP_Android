package com.example.myapplication6.Data.Singelton;

import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Expense;

import java.util.ArrayList;
import java.util.List;

public class AllData {
    private static AllData instance = null;

    private List<Category> categories;

    public AllData() {
        this.categories = new ArrayList<>();

        Category foodC = new Category("food");
        /*foodC.addExpense(new Expense(5.4f,"1","Starbucks"));
        foodC.addExpense(new Expense(5.4f,"1","Starbucks"));
        foodC.addExpense(new Expense(5.4f,"1","Starbucks"));
        foodC.addExpense(new Expense(5.4f,"1","Starbucks"));
        foodC.addExpense(new Expense(5.4f,"1","Starbucks"));*/
        categories.add(foodC);

        Category freetimeC = new Category("Freizeit");
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

    public static void main(String[] args) {
        AllData data = new AllData();
        System.out.println(data.getCategories().get(1).getExpenses());
    }

    public int searchForCategory(String categoryName){
        int reValue = 0;

        for(int i = 0; i < getCategories().size()-1; ++i){
            if(categoryName.equals(getCategories().get(i).getName())){
                break;
            }else{
                reValue++;
            }
        }

        return reValue;
    }
}
