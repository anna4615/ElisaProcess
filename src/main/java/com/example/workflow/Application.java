package com.example.workflow;

import com.example.workflow.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;

import static org.camunda.spin.Spin.JSON;

@SpringBootApplication
public class Application {

  public static void main(String... args) throws JsonProcessingException {
    //SpringApplication.run(Application.class, args);

//    String resultVariable = "{\"pos\":1,\"value\":0.45};{\"pos\":2,\"value\":1.02};{\"pos\":73,\"value\":0.11};{\"pos\":74,\"value\":0.165};{\"pos\":75,\"value\":0.247};{\"pos\":76,\"value\":0.371};{\"pos\":77,\"value\":0.556};{\"pos\":78,\"value\":0.835};{\"pos\":79,\"value\":1.252};{\"pos\":80,\"value\":1.879};{\"pos\":81,\"value\":0.12};{\"pos\":82,\"value\":0.175};{\"pos\":83,\"value\":0.257};{\"pos\":84,\"value\":0.381};{\"pos\":85,\"value\":0.566};{\"pos\":86,\"value\":0.845};{\"pos\":87,\"value\":1.262};{\"pos\":88,\"value\":1.889};{\"pos\":89,\"value\":0.09};{\"pos\":90,\"value\":0.145};{\"pos\":91,\"value\":0.227};{\"pos\":92,\"value\":0.351};{\"pos\":93,\"value\":0.536};{\"pos\":94,\"value\":0.815};{\"pos\":95,\"value\":1.232};{\"pos\":96,\"value\":1.859}";
//    String[] resultsArray = resultVariable.split(";");

    //TODO: mappa input från instrument till ArrayList<RawData>
    //ArrayList<RawData> rawDatas = new ArrayList<RawData>();
    StandardCurve stdCurve = new StandardCurve();

   // stdCurve.setStdDatas(new ArrayList<StandardData>());


//    for(String result : resultsArray){
//      JSONObject resultJson = new JSONObject(result);
//      results.put(resultJson.getInt("pos"), resultJson.getDouble("value"));

    //TODO: results skall hämtas från variabel standardData i processen


    //calculateStandard(stdConcentrations, standardResults);

  }



//
//      String testsVariable = "[{\"id\":138,\"sampleId\":3,\"elisaId\":100,\"sampleName\":\"Prov3\",\"measuredValue\":0.0,\"concentration\":0.0,\"platePosition\":1,\"status\":\"In Progress\"},{\"id\":139,\"sampleId\":4,\"elisaId\":100,\"sampleName\":\"Prov4\",\"measuredValue\":0.0,\"concentration\":0.0,\"platePosition\":2,\"status\":\"In Progress\"}]";
//      String plateVariable = "{\"elisaId\":124,\"wells\":[{\"pos\":1,\"wellName\":\"A1\",\"reagent\":\"Prov3\"},{\"pos\":2,\"wellName\":\"B1\",\"reagent\":\"Prov4\"},{\"pos\":73,\"wellName\":\"A10\",\"reagent\":\"0.25 ug\\\\ml\"},{\"pos\":74,\"wellName\":\"B10\",\"reagent\":\"0.5 ug\\\\ml\"},{\"pos\":75,\"wellName\":\"C10\",\"reagent\":\"1.0 ug\\\\ml\"},{\"pos\":76,\"wellName\":\"D10\",\"reagent\":\"2.0 ug\\\\ml\"},{\"pos\":77,\"wellName\":\"E10\",\"reagent\":\"4.0 ug\\\\ml\"},{\"pos\":78,\"wellName\":\"F10\",\"reagent\":\"8.0 ug\\\\ml\"},{\"pos\":79,\"wellName\":\"G10\",\"reagent\":\"16.0 ug\\\\ml\"},{\"pos\":80,\"wellName\":\"H10\",\"reagent\":\"32.0 ug\\\\ml\"},{\"pos\":81,\"wellName\":\"A11\",\"reagent\":\"0.25 ug\\\\ml\"},{\"pos\":82,\"wellName\":\"B11\",\"reagent\":\"0.5 ug\\\\ml\"},{\"pos\":83,\"wellName\":\"C11\",\"reagent\":\"1.0 ug\\\\ml\"},{\"pos\":84,\"wellName\":\"D11\",\"reagent\":\"2.0 ug\\\\ml\"},{\"pos\":85,\"wellName\":\"E11\",\"reagent\":\"4.0 ug\\\\ml\"},{\"pos\":86,\"wellName\":\"F11\",\"reagent\":\"8.0 ug\\\\ml\"},{\"pos\":87,\"wellName\":\"G11\",\"reagent\":\"16.0 ug\\\\ml\"},{\"pos\":88,\"wellName\":\"H11\",\"reagent\":\"32.0 ug\\\\ml\"},{\"pos\":89,\"wellName\":\"A12\",\"reagent\":\"0.25 ug\\\\ml\"},{\"pos\":90,\"wellName\":\"B12\",\"reagent\":\"0.5 ug\\\\ml\"},{\"pos\":91,\"wellName\":\"C12\",\"reagent\":\"1.0 ug\\\\ml\"},{\"pos\":92,\"wellName\":\"D12\",\"reagent\":\"2.0 ug\\\\ml\"},{\"pos\":93,\"wellName\":\"E12\",\"reagent\":\"4.0 ug\\\\ml\"},{\"pos\":94,\"wellName\":\"F12\",\"reagent\":\"8.0 ug\\\\ml\"},{\"pos\":95,\"wellName\":\"G12\",\"reagent\":\"16.0 ug\\\\ml\"},{\"pos\":96,\"wellName\":\"H12\",\"reagent\":\"32.0 ug\\\\ml\"}]}";
//
//      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//      Plate plate = objectMapper.readValue(plateVariable, new TypeReference<>() {});
//
//      var tests2 = JSON(plate);
//
//      var t = tests2.value();



  private static void calculateAverageOfTriplicates(ArrayList<Double> stdConcentrations, HashMap<Integer, Double> standardResults) {

    //StandardCurve stdCurve = new StandardCurve();



    //return stdCurve;
  }



}


