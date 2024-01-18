package com.example.myapplication6.Data.Singelton;

import com.example.myapplication6.Data.Category;
import com.example.myapplication6.Data.Expense;
import com.example.myapplication6.Data.Saving;
import com.example.myapplication6.Data.Savingplan;
import java.util.ArrayList;
import java.util.List;

public class AllData {
    private static AllData instance = null;
    private List<Category> categories;
    private List<Savingplan> savingplans;
    private int selectedView;
    private float currentPercentageOfGoal; // noch in arbeit - nicht anfassen
    private Expense selectedExpense;

    public AllData() {
        this.categories = new ArrayList<>();
        this.savingplans = new ArrayList<>();
        this.selectedExpense = new Expense(0,"0","",0);

        // Kategorien - categories

        Category foodC = new Category("Food", 1);
        foodC.addExpense(new Expense(5.4f,"07.05.2023","Starbucks",1));
        foodC.addExpense(new Expense(6.4f,"18.08.2023","Starbucks",2));
        foodC.addExpense(new Expense(7.4f,"27.01.2023","Starbucks",3));
        foodC.addExpense(new Expense(8.4f,"19.12.2023","Starbucks",4));
        foodC.addExpense(new Expense(9.4f,"01.07.2023","Starbucks",5));
        categories.add(foodC);

        Category freetimeC = new Category("Freizeit", 2);
        freetimeC.addExpense(new Expense(5.4f,"06.12.2023","FIF",1));
        freetimeC.addExpense(new Expense(6.4f,"09.09.2023","FIF",2));
        freetimeC.addExpense(new Expense(7.4f,"17.12.2023","FIF",3));
        categories.add(freetimeC);

        // Sparpläne - savingplans

        Savingplan carSP = new Savingplan(1,4000,"Auto");
        carSP.addSaving(new Saving(1,500,"01.01.2023"));
        carSP.addSaving(new Saving(2,1500,"01.01.2023"));
        savingplans.add(carSP);
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

    public void deleteCategory(Category category){
        if(checkCategoryInList(category)){
            categories.remove(category);
        }
    }

    public boolean checkCategoryInList(Category category){
        boolean reValue = false;

        if(categories.contains(category)){
            reValue = true;
        }

        return reValue;
    }

    public void deleteExpense(Expense expense){
        if(checkExpenseInList(expense)){
            getSelectedCategory(getSelectedView()).getExpenses().remove(expense);
        }
    }

    public boolean checkExpenseInList(Expense expense){
        boolean reValue = false;

        if(getSelectedCategory(getSelectedView()).getExpenses().contains(expense)){
            reValue = true;
        }

        return reValue;
    }

    public int searchForSavingplanIndex(String savingplanName) {
        int index = 0;

        for (int i = 0; i < savingplans.size(); i++) {
            if (savingplans.get(i).getName().equals(savingplanName)) {
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

    public int getNextIdSaving(Savingplan selectedSavingplan){
        return selectedSavingplan.getSavings().size()+1;
    }

    public int getNextIdSavingplan(){
        int maxId = 0;

        for (Savingplan savingplan : savingplans) {
            if (savingplan.getId() > maxId) {
                maxId = savingplan.getId();
            }
        }

        return maxId + 1;
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
    // man kann ändern I know
    public Category getSelectedCategory(int id){
        Category reValue = new Category("",0);

        for(int i = 0; i < categories.size(); ++i){
            if(categories.get(i).getId() == id){
                reValue = categories.get(i);
                break;
            }
        }

        return reValue;
    }

    public void setSelectedExpense(Expense selectedExpense) {
        this.selectedExpense = selectedExpense;
    }

    public Expense getSelectedExpense() {
        return selectedExpense;
    }

    public int getSelectedView() {
        return selectedView;
    }

    public void setSelectedView(int selectedView) {
        this.selectedView = selectedView;
    }

    public List<Savingplan> getSavingplans() {
        return savingplans;
    }
}
