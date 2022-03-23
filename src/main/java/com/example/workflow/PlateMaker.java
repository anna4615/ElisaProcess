package com.example.workflow;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("PlateMaker")
public class PlateMaker implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        //Assay.Id = ProcessInstansId, senare skall nog assayen med pover sparats i
        //databasen innan processen startas och så skickas id för assayen som en variabel
        //i bodyn för REST-anropet
        String assayId = execution.getProcessInstanceId();

        String sampleString = (String) execution.getVariable("samples");
        String[] sampleNames = sampleString.split(";");

        ArrayList<Sample> samples = new ArrayList<>();

        for (int i = 0; i < sampleNames.length; i++) {
            Sample sample = new Sample(sampleNames[i]);
            samples.add(sample);
        }

        Assay assay = new Assay(assayId, samples);

        //https://docs.camunda.org/manual/7.5/user-guide/process-engine/variables/#object-value-serialization
        ObjectValue assayValue = Variables.objectValue(assay)
                .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
                .create();

        execution.setVariable("Assay", assayValue);
    }
}
