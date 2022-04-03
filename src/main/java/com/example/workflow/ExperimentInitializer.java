package com.example.workflow;

import camundajar.impl.scala.collection.Map;
import com.example.workflow.models.Elisa;
import com.example.workflow.models.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.Spin;
import org.camunda.spin.SpinList;
import org.camunda.spin.json.SpinJsonNode;
import org.springframework.stereotype.Component;
import spinjar.com.minidev.json.JSONValue;
import org.json.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.camunda.spin.Spin.*;
import static org.camunda.spin.DataFormats.*;


@Component("ExperimentInitializer")
public class ExperimentInitializer implements  JavaDelegate{

    private static final String LIMS_API_URL = "http://localhost:3627/graphql";
    private HttpClient client = HttpClient.newHttpClient();

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        int elisaId = createElisa();
        execution.setVariable("ElisaId", elisaId);

        String sampleString = (String) execution.getVariable("samples");
        String[] samplesString = sampleString.split(";");
        //TODO: felhantering om fler än 72 prover, dvs samplesString.length > 72
        Sample[] samples = new Sample[samplesString.length];

        //TODO: använd Spin för att mappa Json till Sample
        for (int i = 0; i < samples.length; i++) {
            JSONObject sampleJson = new JSONObject(samplesString[i]);
            int id = sampleJson.getInt("id");
            String name = sampleJson.getString("name");
            Sample sample = new Sample(id, name);
            samples[i] = sample;
        }

        ArrayList<Test> testList = new ArrayList<>();
        int position = 1;

        for (int i = 0; i < samples.length; i++) {
            Test test = createTest(samples[i].getId(), samples[i].getName(), elisaId, position);
            testList.add(test);
            position++;
        }

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

    private Test createTest(int sampleId, String sampleName, int elisaId, int position) throws IOException, InterruptedException {

        String query = "{\"query\":\"mutation{addTest(input:{sampleId:" + sampleId +
                                                            ",elisaId:" + elisaId +
                                                            ",elisaPlatePosition:" + position +
                                                            "}){test{id,sampleId,elisaId,elisaPlatePosition,status,dateAdded}}}\"}";

        HttpRequest addTest = HttpRequest.newBuilder()
                .uri(URI.create(LIMS_API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        HttpResponse<String> mutationResponse = client.send(addTest, HttpResponse.BodyHandlers.ofString());

        JSONObject testJson = new JSONObject(mutationResponse.body())
                .getJSONObject("data")
                .getJSONObject("addTest")
                .getJSONObject("test");

        int id = testJson.getInt("id");
        String status = testJson.getString("status");

        Test test = new Test(id, sampleId, sampleName, elisaId, position, status);

        return test;
    }
}
