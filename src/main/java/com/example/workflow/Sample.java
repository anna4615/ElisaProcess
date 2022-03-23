package com.example.workflow;

public class Sample {

    private String name;
    private double measuredValue;
    private double concentration;

    public Sample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
    }

    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }
}
