package com.example.myapplication6.Data;

public class Expense {
    float amount;
    String date;

    String name;

    public Expense(float amount, String date, String name) {
        this.amount = amount;
        this.date = date;
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "amount: " + amount + " date: " + date + "name: " + name +"\n";
    }
}
