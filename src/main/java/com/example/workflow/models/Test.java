package com.example.workflow.models;

public class Test {

    private int id;
    private int sampleId;
    private int elisaId;
    private String sampleName;
    private float measuredValue;
    private float concentration;
    private int elisaPlatePosition;
    private String status;

    public Test() {
    }

    public Test(int id, int sampleId, String sampleName, int elisaId, int elisaPlatePosition,
                String status) {
        this.id = id;
        this.sampleId = sampleId;
        this.sampleName = sampleName;
        this.elisaId = elisaId;
        this.elisaPlatePosition = elisaPlatePosition;
        this.status = status;
    }



    public int getId() {
        return id;
    }

    public float getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(float measuredValue) {
        this.measuredValue = measuredValue;
    }

    public float getConcentration() {
        return concentration;
    }

    public void setConcentration(float concentration) {
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

//    public void setElisaPlatePosition(int elisaPlatePosition) {
//        this.elisaPlatePosition = elisaPlatePosition;
//    }

    public int getElisaPlatePosition() {
        return elisaPlatePosition;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleName() {
        return sampleName;
    }
}
