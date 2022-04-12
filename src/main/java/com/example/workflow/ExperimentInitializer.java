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
    //private static final String LIMS_API_URL = "http://localhost:5000/graphql/";
    private GraphQL graphQL;
    private HttpClient client ;
    private int elisaId;

    public ExperimentInitializer() {
        this.graphQL = new GraphQL();
        this.client= HttpClient.newHttpClient();
    }
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        //TODO: här bör det framgå att ELISAn sparas i DB
        elisaId = createElisa();
        execution.setVariable("elisaId", elisaId);

        //String s = (String) execution.getVariable("samples");
        String[] samplesInput = ((String) execution.getVariable("samples")).split(";");
        //TODO: felhantering om fler än 72 prover, dvs samplesInput.length > 72

        //TODO: här bör det framgå att testerna sparas i DB
        ArrayList<Test> testList = createTestList(samplesInput);
        String tests = JSON(testList).toString();
        execution.setVariable("tests", tests);

    }


    private int createElisa() throws IOException, InterruptedException {

        String query = "{\"query\":\"mutation{addElisa{elisa{id,status,dateAdded}}}\\n\"}";
        JSONObject response = graphQL.sendQuery(query);

        int id = response.getJSONObject("data")
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
        JSONObject response = graphQL.sendQuery(query);

        JSONObject testJson = response.getJSONObject("data")
                                      .getJSONObject("addTest")
                                      .getJSONObject("test");

        int id = testJson.getInt("id");
        String status = testJson.getString("status");

        Test test = new Test(id, sampleId, sampleName, elisaId, position, status);

        return test;
    }
}
