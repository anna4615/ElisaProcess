package com.example.workflow.models;

import java.util.HashMap;

public class StandardCurve {

    StandardData[] stdDatas;
    double[] concentrations;
    double[] aveValues;
    private double k;
    private double m;

    public StandardCurve() {
        this.stdDatas = new StandardData[24];
        this.concentrations =  new double[8];
        this.aveValues = new double[8];
        getTestData();
        calculateAverages();
        setK();
        setM();
    }

    public StandardData[] getStdDatas() {
        return stdDatas;
    }

    public double getK() {
        return k;
    }

    public void setK() {

        for (int i = 0; i < 7; i++) {
            double deltaConc = concentrations[i + 1] - concentrations[i];
            double deltaValue = aveValues[i + 1] - aveValues[i];
            k+= (deltaConc / deltaValue);
        }
        k /= 7;
    }

    public double getM() {
        return m;
    }

    public void setM() {

        for (int i = 0; i < 8; i++) {
//            double c = concentrations[i];
//            double v = aveValues[i];
//            double kTimesv = k * v;
//            m = c -kTimesv;

            m+= ((concentrations[i]) - (k * aveValues[i]));
        }

        m /= 8;
    }


    public void calculateAverages(){

        for (int i = 0; i < 8; i++) {
            double value1 = stdDatas[i].getValue();
            double value2 = stdDatas[i + 8].getValue();
            double value3 = stdDatas[i + 16].getValue();
            double average = (value1 + value2 + value3) / 3;
            aveValues[i] = average;
            concentrations[i] = stdDatas[i].getConcentration();
        }
    }


    private void getTestData(){
        int pos = 0;
        for (int i = 0; i < 3; i++) {
            double conc = 100.0;
            double value = 0.1 + (i * 0.01);
            for (int j = 0; j < 8; j++) {
                stdDatas[pos] = new StandardData(conc, value);
                value *= 1.5;
                conc *= 1.5;
                pos++;
            }
        }
    }



}
