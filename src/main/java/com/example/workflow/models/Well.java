package com.example.workflow.models;

public class Well {

    private int pos;
    private String wellName;
    private String reagent;

    public Well(int pos, String wellName/*, String reagent*/) {
        this.pos = pos;
        this.wellName = wellName;
        //this.reagent = reagent;
    }

    public int getPos() {
        return pos;
    }

    public String getWellName() {
        return wellName;
    }

    public void setReagent(String reagent) {
        this.reagent = reagent;
    }

    public String getReagent() {
        return reagent;
    }
}