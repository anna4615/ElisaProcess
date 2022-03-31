package com.example.workflow.models;

public class Test {

    private String sampleId;
    private int elisaId;
    private double measuredValue;
    private double concentration;
    private int platePosition;
    private String status;

    public Test(String sampleId, int elisaId, int platePosition) {
        this.sampleId = sampleId;
        this.elisaId = elisaId;
        this.platePosition = platePosition;
        status = "In Progress";
    }
}
