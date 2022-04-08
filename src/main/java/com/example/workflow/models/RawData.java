package com.example.workflow.models;

public class RawData {

    private final int position;
    private final String reagent;
    private final float measValue;

    public RawData(int position, String reagent, float value) {
        this.position = position;
        this.reagent = reagent;
        this.measValue = value;
    }

    public int getPosition() {
        return position;
    }

    public String getReagent() {
        return reagent;
    }

    public double getMeasValue() {
        return measValue;
    }
}
