package com.example.myapplication6.Data;

import java.util.ArrayList;

public class Category {
    private int id;

    private String name;
    private ArrayList<Expense> expenses = new ArrayList<Expense>();

    public Category(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    @Override
    public String toString() {return "" + name;}
}
