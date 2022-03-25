package com.example.workflow;

import com.example.workflow.models.Elisa;
import com.example.workflow.models.Sample;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("ExperimentInitializer")
public class ExperimentInitializer implements  JavaDelegate{
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        //Elisa.Id = ProcessInstansId, senare skall nog ELISAn med pover sparats i
        //databasen innan processen startas och så skickas id för assayen som en variabel
        //i bodyn för REST-anropet
        //String elisaId = execution.getProcessInstanceId();

        String sampleString = (String) execution.getVariable("samples");
        String[] sampleNames = sampleString.split(";");

        //TODO: felhantering om fler än 72 prover, dvs sampleNames.length > 72

        ArrayList<Sample> samples = new ArrayList<>();
        int position = 1;

        for (int i = 0; i < sampleNames.length; i++) {
            Sample sample = new Sample(sampleNames[i], position, "In ELISA");
            samples.add(sample);
            position++;
        }

        Elisa elisa = new Elisa(samples);
        //TODO: spara Elisa i DB
        //elisa.setId(id från DB);

        elisa.setId(execution.getProcessInstanceId());

        execution.setVariable("ElisaId", elisa.getId());



        //Försökte spara objekt som processvariabel men fick det inte att funka men insåg att det nog  räcker med AssayId
        //https://docs.camunda.org/manual/7.5/user-guide/process-engine/variables/#object-value-serialization
//        ObjectValue assayValue = Variables.objectValue(assay)
//                .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
//                .create();
//
//        execution.setVariable("Elisa", assayValue);

//        ObjectValue samplesValue = Variables.objectValue(samples)
//                .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
//                .create();
//
//        execution.setVariable("Samples", samplesValue);
    }
}
