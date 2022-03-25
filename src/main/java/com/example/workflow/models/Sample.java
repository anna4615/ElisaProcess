package com.example.workflow.models;

public class Sample {

    private String name;
    private double measuredValue;
    private double concentration;
    private int platePosition;
    private String status;

    public Sample(String name, int platePosition, String status) {
        this.name = name;
        this.platePosition = platePosition;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getPlatePosition() {
        return platePosition;
    }

    public void setPlatePosition(int platePosition) {
        this.platePosition = platePosition;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
