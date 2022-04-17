package com.example.workflow.Delegates;

import com.example.workflow.GraphQL;
import com.example.workflow.Models.*;
import com.example.workflow.Models.DaoModels.Elisa;
import com.example.workflow.Models.DaoModels.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component("ResultCalculator")
public class ResultCalculator implements JavaDelegate {

    private ArrayList<Test> tests;
    private  ObjectMapper objectMapper;
    private GraphQL graphQL;

    public ResultCalculator() {
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.graphQL = new GraphQL();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        //resultat för standardkurva från instrument
        // Innehåller position, koncentration, mätvärde
        String standardsDataVariable = execution.getVariable("standardsData").toString();
        StandardData[] stdDatas = objectMapper.readValue(standardsDataVariable, new TypeReference<>() { });
        //Gör ny standardkurva med värden från instrumentet
        StandardCurve stdCurve = new StandardCurve(stdDatas);

        //resultat för prover från instrument
        // Innehåller position, sampleId, provets namn, mätvärde
        String samplesDataVariable = execution.getVariable("samplesData").toString();
        JSONArray samplesData = new JSONArray(samplesDataVariable);

        //ELISA med tester hämtas från DB
        int elisaId = Integer.parseInt(execution.getVariable("elisaId").toString());
        String query = "{\"query\":\"query{elisas(where:{id:{eq:" + elisaId + "}}){id,tests{id,sampleId,elisaId,elisaPlatePosition,status,sample{id,name}}}}\"}";
        JSONObject response = graphQL.sendQuery(query);
        //svaret innehåller en lista av ELISAs med en enda ELISA
        JSONObject elisaJson = response.getJSONObject("data").getJSONArray("elisas").getJSONObject(0);
        Elisa elisa = objectMapper.readValue(elisaJson.toString(), new TypeReference<>() {});

        //Ta fram rätt test från ELISAns lista mhja sampleId i sampleData från instrumentet
        //Sätt mätvärde till värde från instrument
        // Beräkna koncentration för prov, ge koncentrationen till testet
        for (Object sampleData : samplesData){
            JSONObject sampleDataJson = (JSONObject) sampleData;
            Test test = elisa.getTests().stream()
                    .filter(t -> t.getSampleId() == sampleDataJson.getInt("sampleId"))
                    .findFirst()
                    .get();
            test.setMeasuredValue(sampleDataJson.getFloat("measValue"));
            test.setConcentration(stdCurve.calculateConc(test.getMeasuredValue()));
        }

        ObjectValue elisaValue = Variables.objectValue(elisa)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create();

        execution.setVariable("elisa", elisaValue);
    }
}
