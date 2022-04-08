package com.example.workflow;

import com.example.workflow.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component("ResultCalculator")
public class ResultCalculator implements JavaDelegate {

    private ArrayList<Test> tests;
    private StandardData[] stdDatas;
    private  ObjectMapper objectMapper;
    private GraphQL graphQL;

    public ResultCalculator() {
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.graphQL = new GraphQL();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String standardsDataVariable = execution.getVariable("standardsData").toString();
        stdDatas = objectMapper.readValue(standardsDataVariable, new TypeReference<>() {});
        StandardCurve stdCurve = new StandardCurve(stdDatas);

        String samplesDataVariable = execution.getVariable("samplesData").toString();
        JSONArray samplesDataArray = new JSONArray(samplesDataVariable);


        int elisaId = Integer.parseInt(execution.getVariable("elisaId").toString());
        String query = "{\"query\":\"query{elisas(where:{id:{eq:" + elisaId + "}}){id,tests{id,sampleId,sample{id,name}}}}\"}";
        JSONObject response = graphQL.sendQuery(query);

        JSONObject elisaJson = response.getJSONObject("data").getJSONArray("elisas").getJSONObject(0);
        Elisa elisa = objectMapper.readValue(elisaJson.toString(), new TypeReference<>() {});

        for (Object sampleData : samplesDataArray){
            var sampleJson = new JSONObject(sampleData.toString());
            float measValue = sampleJson.getFloat("measValue");
            float conc = stdCurve.calculateConc(measValue);
            int sampleId = sampleJson.getInt("sampleId");
            Test test = elisa.getTests().stream().filter(t -> t.getSampleId() == sampleId).findFirst().get();
            test.setConcentration(conc);
        }

        ObjectValue elisaValue = Variables.objectValue(elisa)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create();

        execution.setVariable("elisa", elisaValue);
    }

}
