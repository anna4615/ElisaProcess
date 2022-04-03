package com.example.workflow.models;

public class Sample {

    private int id;
    private String name;
    //private double concentration;

    public Sample(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public double getConcentration() {
//        return concentration;
//    }
//
//    public void setConcentration(double concentration) {
//        this.concentration = concentration;
//    }

}
