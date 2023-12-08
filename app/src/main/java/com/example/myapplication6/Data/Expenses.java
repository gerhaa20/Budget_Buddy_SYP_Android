package com.example.myapplication6.Data;

public class Expenses {
    float amount;
    String date;

    public Expenses(float amount, String date) {
        this.amount = amount;
        this.date = date;
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
        return "" + amount;
    }
}
