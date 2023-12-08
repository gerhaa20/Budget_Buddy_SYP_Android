package com.example.myapplication6.Data;

import java.util.ArrayList;

public class Categorie {
    String name;
    ArrayList<Expenses> expenses = new ArrayList<>();

    public Categorie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Expenses> getExpenses() {
        return expenses;
    }

    public void addExpense(Expenses expense){
        expenses.add(expense);
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
