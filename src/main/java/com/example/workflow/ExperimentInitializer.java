package com.example.workflow;
import com.example.workflow.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;
import org.json.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.camunda.spin.Spin.*;


@Component("ExperimentInitializer")
public class ExperimentInitializer implements  JavaDelegate{

    //private static final String LIMS_API_URL = "http://localhost:3627/graphql";
    private static final String LIMS_API_URL = "http://localhost:5000/graphql/";
    private HttpClient client = HttpClient.newHttpClient();
    private int elisaId;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        elisaId = createElisa();
        execution.setVariable("elisaId", elisaId);

        //String s = (String) execution.getVariable("samples");
        String[] samplesInput = ((String) execution.getVariable("samples")).split(";");
        //TODO: felhantering om fler än 72 prover, dvs samplesInput.length > 72

        ArrayList<Test> testList = createTestList(samplesInput);
        String tests = JSON(testList).toString();
        execution.setVariable("tests", tests);

    }


    private int createElisa() throws IOException, InterruptedException {

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

    private ArrayList<Test> createTestList(String[] samplesInput) throws IOException, InterruptedException {

        ArrayList<Test> testList = new ArrayList<>();
        int position = 1;

        for (String input : samplesInput){
            JSONObject inputJson = new JSONObject(input);
            Test test = createTest(inputJson.getInt("id"), inputJson.getString("name"), position);
            testList.add(test);
            position++;
        }

        return testList;
    }

    private Test createTest(int sampleId, String sampleName, int position) throws IOException, InterruptedException {

        String query = "{\"query\":\"mutation{addTest(input:{sampleId:" + sampleId +
                                                            ",elisaId:" + elisaId +
                                                            ",elisaPlatePosition:" + position +
                                                            "}){test{id,sampleId,elisaId,elisaPlatePosition,status,dateAdded}}}\"}";

        HttpRequest addTest2 = HttpRequest.newBuilder()
                .uri(URI.create(LIMS_API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        HttpResponse<String> mutationResponse = client.send(addTest2, HttpResponse.BodyHandlers.ofString());

        JSONObject testJson = new JSONObject(mutationResponse.body())
                .getJSONObject("data")
                .getJSONObject("addTest")
                .getJSONObject("test");

//        //Så här hade man kunnat göra men det blir mer kod och jag tycker det är bättre säkerhet att sätta alla värden i
//        //konstruktorn och ta bort alla setters
//        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        Test test2 = objectMapper.readValue(testJson.toString(), new TypeReference<>() {});
//        test2.setPlatePosition(position);
//        test2.setSampleName(sampleName);

        int id = testJson.getInt("id");
        String status = testJson.getString("status");

        Test test = new Test(id, sampleId, sampleName, elisaId, position, status);

        return test;
    }
}
