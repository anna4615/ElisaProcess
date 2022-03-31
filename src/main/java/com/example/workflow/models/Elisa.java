package com.example.workflow.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Elisa implements Serializable{

    private String id;
    private ArrayList<Test> tests;
    private String status;

    public Elisa(/*String id, ArrayList<Sample> samples*/) {
        //this.id = id;
        //this.samples = samples;
        //status = "In progress";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }
}
