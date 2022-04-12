package com.example.workflow.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {

    private int id;
    private int sampleId;
    private int elisaId;
    private String sampleName;
    private float measuredValue;
    private float concentration;
    private int platePosition;
    private String status;


    public Test() {

    }

    public Test(int id, int sampleId, String sampleName, int elisaId, int platePosition,
                String status) {
        this.id = id;
        this.sampleId = sampleId;
        this.sampleName = sampleName;
        this.elisaId = elisaId;
        this.platePosition = platePosition;
        this.status = status;
    }

    //https://www.baeldung.com/jackson-nested-values
    @SuppressWarnings("unchecked")
    @JsonProperty("sample")
    private void unpackNested(java.util.Map<String,Object> sample) {
        this.sampleName = (String) sample.get("name");
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

    public void setPlatePosition(int platePosition) {
        this.platePosition = platePosition;
    }

    @JsonAlias({"elisaPlatePosition", "platePosition"})
    public int getPlatePosition() {
        return platePosition;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleName() {
        return sampleName;
    }
}
