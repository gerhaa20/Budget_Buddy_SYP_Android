package com.example.myapplication6.Data;

import java.util.ArrayList;

public class Savingplan {
    private int id;
    private float goal;
    private float currentMoney;
    private String name;

    private ArrayList<Saving> savings = new ArrayList<Saving>();

    public Savingplan(int id, float goal, String name) {
        this.id = id;
        this.goal = goal;
        this.currentMoney = 0;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCurrentMoney() {
        return currentMoney;
    }

    public String getName() {
        return name;
    }

    public float getGoal() {
        return goal;
    }

    public void addSaving(Saving saving){
        currentMoney += saving.getAmount();
        savings.add(saving);
    }

    public ArrayList<Saving> getSavings() {
        return savings;
    }

    @Override
    public String toString() {return "" + name;}
}
