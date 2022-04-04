package com.example.workflow;


import com.example.workflow.models.Elisa;
import com.example.workflow.models.Plate;
import com.example.workflow.models.Sample;
import com.example.workflow.models.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("PlateMaker")
public class PlateMaker implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        //String testsVariable = "[{\"id\":138,\"sampleId\":3,\"elisaId\":100,\"sampleName\":\"Prov3\",\"measuredValue\":0.0,\"concentration\":0.0,\"platePosition\":1,\"status\":\"In Progress\"},{\"id\":139,\"sampleId\":4,\"elisaId\":100,\"sampleName\":\"Prov4\",\"measuredValue\":0.0,\"concentration\":0.0,\"platePosition\":2,\"status\":\"In Progress\"}]";

        String testsVariable = execution.getVariable("tests").toString();
        int elisaId = Integer.parseInt(execution.getVariable("ElisaId").toString());

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ArrayList<Test> tests = objectMapper.readValue(testsVariable, new TypeReference<>() {});

        Plate plate = new Plate(elisaId, tests);

        execution.setVariable("plate", plate);
    }
}
