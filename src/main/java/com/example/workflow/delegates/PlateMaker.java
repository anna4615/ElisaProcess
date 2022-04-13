package com.example.workflow.delegates;


import com.example.workflow.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.camunda.spin.Spin.JSON;

@Component("PlateMaker")
public class PlateMaker implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        //String testsVariable = "[{\"id\":138,\"sampleId\":3,\"elisaId\":100,\"sampleName\":\"Prov3\",\"measuredValue\":0.0,\"concentration\":0.0,\"platePosition\":1,\"status\":\"In Progress\"},{\"id\":139,\"sampleId\":4,\"elisaId\":100,\"sampleName\":\"Prov4\",\"measuredValue\":0.0,\"concentration\":0.0,\"platePosition\":2,\"status\":\"In Progress\"}]";

        String testsVariable = execution.getVariable("tests").toString();
        int elisaId = Integer.parseInt(execution.getVariable("elisaId").toString());

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ArrayList<Test> tests = objectMapper.readValue(testsVariable, new TypeReference<>() {});

        Plate plate = new Plate(elisaId, 2.0F, 2.0F, tests);

        //TODO: skickas till UI
        ObjectValue plateValue = Variables.objectValue(plate)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create();

        execution.setVariable("plate", plateValue);
    }
}
