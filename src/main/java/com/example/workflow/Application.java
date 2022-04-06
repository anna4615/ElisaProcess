package com.example.workflow;

import com.example.workflow.models.Plate;
import com.example.workflow.models.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import static org.camunda.spin.Spin.JSON;

@SpringBootApplication
public class Application {

  public static void main(String... args) throws JsonProcessingException {
    SpringApplication.run(Application.class, args);

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

  }


}