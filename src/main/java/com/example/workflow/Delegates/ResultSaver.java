package com.example.workflow.Delegates;

import com.example.workflow.GraphQL;
import com.example.workflow.Models.DaoModels.Elisa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.springframework.stereotype.Component;

import static org.camunda.spin.Spin.JSON;

@Component("ResultSaver")
public class ResultSaver implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        TypedValue elisaVariable = execution.getVariableTyped("elisa");
        var elisa = (Elisa) elisaVariable.getValue();
        boolean experimentOkVariable = (boolean) execution.getVariable("experimentOk");

        String elisaStatus = null;
        String testStatus = null;

        if (experimentOkVariable) {
            elisaStatus = "\\\"Approved\\\"";
            testStatus = "\\\"Approved\\\"";
        }
        else{
            elisaStatus = "\\\"Failed\\\"";
            testStatus = "\\\"Failed\\\"";
        }

        String query = "{\"query\":\"mutation{saveElisaResult(elisaInput:" +
                "{id:" + elisa.getId() +
                ",status:" + elisaStatus +
                ",testInputs:" + elisa.getTestsForSaveResult(testStatus) +
                "}){elisa{id,status,tests{concentration,sample{id,name}}}}}\"}";

        GraphQL graphQL = new GraphQL();
        graphQL.sendQuery(query);

        //TODO: uppdatera processvariabel "elisa" med värden sparade i db
        // för att kunna visa slutgiltiga värden i UI

    }
}
