package com.example.workflow.models;

public class RawData {

    private final int position;
    private final String reagent;
    private final double value;

    public RawData(int position, String reagent, double value) {
        this.position = position;
        this.reagent = reagent;
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public String getReagent() {
        return reagent;
    }

    public double getValue() {
        return value;
    }
}
