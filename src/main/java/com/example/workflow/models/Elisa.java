package com.example.workflow.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Elisa implements Serializable{

    private int id;
    private ArrayList<Test> tests;
    private String status;

    public Elisa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }
}
