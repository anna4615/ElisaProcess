package com.example.workflow.models;

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
        //TODO: värden för standard-kurvan skall kommma från instrumentet -> ta bort setTestStd
        setTestStd();
        calculateAverages();
        setKAndM();
    }

    public StandardData[] getStdDatas() {
        return stdDatas;
    }

    public double getK() {
        return k;
    }

    // y = kx + m -> conc = k*value + m
    // k = delta y / delta x -> k = delta conc / delta value
    // m = conc - k*value
    // beräknar k och m för vardera steg i std-kurvan, tar sedan medelvärdet
    public void setKAndM() {
        double tempK = 0.0;
        for (int i = 0; i < 7; i++) {
            double deltaConc = concentrations[i + 1] - concentrations[i];
            double deltaValue = aveValues[i + 1] - aveValues[i];
            tempK = (deltaConc / deltaValue);            k+= tempK;
            m+= concentrations[i] - (tempK * aveValues[i]);
        }
        k /= 7;
        m /= 7;
    }

    public double getM() {
        return m;
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


    private void setTestStd(){
        int pos = 0;
        double conc = 2.0;
        double value = 0.11;
        for (int j = 0; j < 8; j++) {
            stdDatas[pos] = new StandardData(conc, value);
            stdDatas[pos+8] = new StandardData(conc, value + 0.01);
            stdDatas[pos+16] = new StandardData(conc, value - 0.03);
            value *= 1.5;
            conc *= 1.5;
            pos++;
        }
    }
}
