package com.example.workflow;

import com.example.workflow.models.Plate;
import com.example.workflow.models.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("ResultCalculator")
public class ResultCalculator implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String testsVariable = execution.getVariable("tests").toString();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ArrayList<Test> tests = objectMapper.readValue(testsVariable, new TypeReference<>() {});


    }
}
