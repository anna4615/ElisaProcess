package com.example.workflow.Models.DaoModels;

public class Sample {

    private int id;
    private String name;
    private float concentration;

    public Sample(int id, String name, float concentration) {
        this.id = id;
        this.name = name;
        this.concentration = concentration;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getConcentration() {
        return concentration;
    }

    public void setConcentration(float concentration) {
        this.concentration = concentration;
    }

}
