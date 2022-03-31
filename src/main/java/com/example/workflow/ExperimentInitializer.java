package com.example.workflow;

import com.example.workflow.models.Elisa;
import com.example.workflow.models.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import spinjar.com.minidev.json.JSONValue;
import org.json.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component("ExperimentInitializer")
public class ExperimentInitializer implements  JavaDelegate{

    private static final String LIMS_API_URL = "http://localhost:3627/graphql";
    private HttpClient client = HttpClient.newHttpClient();

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        int elisaId = CreateElisa();
        execution.setVariable("ElisaId", elisaId);

        String sampleString = (String) execution.getVariable("samples");
        String[] sampleIds = sampleString.split(";");

        //TODO: felhantering om fler än 72 prover, dvs sampleIds.length > 72

        ArrayList<Test> tests = new ArrayList<Test>();
        int position = 0;

        for (int i = 0; i < sampleIds.length; i++) {
            Test test = new Test(sampleIds[i], elisaId, ++position);
            tests.add(test);
        }
    }


    private int CreateElisa() throws IOException, InterruptedException {

        String query = "{\"query\":\"mutation{addElisa{elisa{id,status,dateAdded}}}\\n\"}";

        HttpRequest addElisa = HttpRequest.newBuilder()
                .uri(URI.create(LIMS_API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        HttpResponse<String> mutationResponse = client.send(addElisa, HttpResponse.BodyHandlers.ofString());

        JSONObject responseJson = new JSONObject(mutationResponse.body());

        int id = responseJson.getJSONObject("data")
                .getJSONObject("addElisa")
                .getJSONObject("elisa")
                .getInt("id");

        return id;
    }
}
