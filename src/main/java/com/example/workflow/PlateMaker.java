package com.example.workflow;


import com.example.workflow.models.Elisa;
import com.example.workflow.models.Sample;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("PlateMaker")
public class PlateMaker implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {


    }
}
