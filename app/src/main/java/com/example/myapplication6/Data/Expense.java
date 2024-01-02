package com.example.myapplication6.Data;

public class Expense {
    private int id;
    private float amount;
    private String date;
    private String name;

    public Expense(float amount, String date, String name, int id) {
        this.amount = amount;
        this.date = date;
        this.name = name;
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "amount: " + amount + " date: " + date + " name: " + name + " id: " + id  +"\n";
    }
}
