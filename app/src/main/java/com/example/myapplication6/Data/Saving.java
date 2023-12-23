package com.example.myapplication6.Data;

public class Saving {
    private int id;
    private float amount;
    private String date;

    public Saving(int id, float amount, String date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Saving{" +
                "id=" + id +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
