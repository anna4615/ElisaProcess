package com.example.workflow.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Plate implements Serializable {

    private int elisaId;
    private ArrayList<Well> wells;

    public Plate() {
    }

    public Plate(int elisaId, ArrayList<Test> tests) {
        this.elisaId = elisaId;
        wells = new ArrayList<Well>();
        createEmptyPlate();
        addStandards();
        addSamples(tests);
    }

    private void createEmptyPlate() {

        int pos = 1;

        for (int i = 1; i < 13; i++) {
            //65 = 'A'
            for (int j = 65; j < 73; j++) {
                String wellName = String.valueOf((char) j) + i;
                Well well = new Well(pos, wellName);
                wells.add(well);
                pos++;
            }
        }
    }

    private void addSamples(ArrayList<Test> tests) {

        for (Test test : tests){
            wells.get(test.getPlatePosition() - 1).setReagent(test.getSampleName());
        }
    }


    private void addStandards(){

        int pos = 72;

        for (int i = 0; i < 3; i++) {
            double conc = 0.25;
            for (int j = 0; j < 8; j++) {
                wells.get(pos).setReagent(conc + " ug\\ml");
                conc*=2;
                pos++;
            }
        }
    }

    public ArrayList<Well> getWells() {
        return wells;
    }

    public int getElisaId() {
        return elisaId;
    }
}
