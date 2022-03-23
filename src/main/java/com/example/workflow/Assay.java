package com.example.workflow;

import java.util.ArrayList;
import java.util.List;

public class Assay {

    private String id;
    private ArrayList<Sample> samples;

    public Assay(String id, ArrayList<Sample> samples) {
        this.id = id;
        this.samples = samples;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }
}
