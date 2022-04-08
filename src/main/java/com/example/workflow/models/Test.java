package com.example.workflow.models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

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



    public int getId() {
        return id;
    }

    public double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(float measuredValue) {
        this.measuredValue = measuredValue;
    }

    public double getConcentration() {
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

//    public void setPlatePosition(int platePosition) {
//        this.platePosition = platePosition;
//    }

    public int getPlatePosition() {
        return platePosition;
    }

//    public void setSampleName(String sampleName) {
//        this.sampleName = sampleName;
//    }

    public String getSampleName() {
        return sampleName;
    }
}
