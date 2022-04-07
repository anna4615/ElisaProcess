package com.example.workflow.models;

public class StandardData {

    //private final int position;
    private final double concentration;
    private final double value;

    public StandardData(/*int position,*/ double concentration, double value) {
       // this.position = position;
        this.concentration = concentration;
        this.value = value;
    }

//    public int getPosition() {
//        return position;
//    }

    public double getConcentration() {
        return concentration;
    }

    public double getValue() {
        return value;
    }
}
