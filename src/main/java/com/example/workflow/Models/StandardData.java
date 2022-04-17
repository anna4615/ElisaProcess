package com.example.workflow.Models;

public class StandardData {

    private int position;
    private float concentration;
    private float measValue;

    public StandardData() {
    }

    public StandardData(int position, float concentration, float value) {
        this.position = position;
        this.concentration = concentration;
        this.measValue = value;
    }

    public int getPosition() {
        return position;
    }

    public float getConcentration() {
        return concentration;
    }

    public float getMeasValue() {
        return measValue;
    }
}
