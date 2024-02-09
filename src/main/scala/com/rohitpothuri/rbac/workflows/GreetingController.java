package com.rohitpothuri.rbac.workflows;

import org.springframework.stereotype.Controller;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.client.WorkflowUpdateException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Controller
public class GreetingController {

    @Autowired WorkflowClient client;


    @PutMapping(
            value = "/")
    ResponseEntity helloSample() {
        GreetingWorkflow workflow =
                client.newWorkflowStub(
                        GreetingWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("GreetingTaskQueue")
                                .setWorkflowId("Greeting")
                                .build());

        return new ResponseEntity<>("\"" + workflow.execute("Rohit") + "\"", HttpStatus.OK);
    }
}
