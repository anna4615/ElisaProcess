package com.example.workflow.models;

import java.util.ArrayList;

public class TestList {

    private ArrayList<Test> tests;

    public TestList() {
        tests = new ArrayList<Test>();
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void add(Test test) {
        tests.add(test);
    }
}
