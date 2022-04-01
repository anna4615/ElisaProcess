package com.example.workflow.models;

public class Test {

    private int id;
    private int sampleId;
    private int elisaId;
    private double measuredValue;
    private double concentration;
    private int platePosition;
    private String status;

    public Test(int id, int sampleId, int elisaId, int platePosition) {
        this.sampleId = sampleId;
        this.elisaId = elisaId;
        this.platePosition = platePosition;
    }



    public int getId() {
        return id;
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

    public int getSampleId() {
        return sampleId;
    }

    public int getElisaId() {
        return elisaId;
    }

    public int getPlatePosition() {
        return platePosition;
    }


}
