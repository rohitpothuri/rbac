package com.rohitpothuri.rbac.workflows;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Promise;
import org.springframework.stereotype.Controller;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.client.WorkflowUpdateException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * Namespaces can be used for envs or for separating conncerns
 * workers need to be restarted whenever there is a change in the workflow code
 */
@Controller
public class GreetingController {

    @Autowired WorkflowClient client;

    @Autowired WorkerFactory workerFactory;

    Promise<String> greeting ;


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
        String greeting =  workflow.execute("Rohit");
        WorkflowExecution workflowDetails = WorkflowStub.fromTyped(workflow).getExecution();
        System.out.println(workflowDetails.getWorkflowId()+"|" +workflowDetails.getRunId()+ " " + greeting);
        return new ResponseEntity<>(workflowDetails.getWorkflowId()+"|" +workflowDetails.getRunId() + " " + greeting, HttpStatus.OK);
    }

    /*@GetMapping(
            value = "/async")
    ResponseEntity helloAsync() {
        GreetingWorkflow workflow =
                client.newWorkflowStub(
                        GreetingWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("GreetingTaskQueue")
                                .setWorkflowId("Greeting")
                                .build());
        greeting = workflow.executeAsync("Rohit Aysnc");
        WorkflowExecution workflowDetails = WorkflowStub.fromTyped(workflow).getExecution();
        System.out.println(workflowDetails.getWorkflowId()+"|" +workflowDetails.getRunId()+ " " + greeting);
        return new ResponseEntity<>(workflowDetails.getWorkflowId()+"|" +workflowDetails.getRunId() + " " + greeting, HttpStatus.OK);
    }

    @GetMapping(
            value = "/greeting")
    ResponseEntity<String> greeting() {
        try{
            return new ResponseEntity<>(greeting.get(), HttpStatus.OK);
        }
        catch(WorkflowUpdateException e){
            System.out.println(e);
            throw e;
        }

    }*/



    @PutMapping(
            value = "/startWorker")
    ResponseEntity startWorker() {
        //f you are using Temporal Cloud, you would instead call the newServiceStubs(WorkflowServiceStubsOptions options)
        // method using options that include the hostname and port number used to reach its frontend service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Specify the name of the Task Queue that this Worker should poll
        Worker worker = factory.newWorker("GreetingTaskQueue");

        // Specify which Workflow implementations this Worker will support
        worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);
        worker.registerActivitiesImplementations(new GreetingActivityImpl());

        // Begin running the Worker
        factory.start();

        return new ResponseEntity<>("Worker started", HttpStatus.OK);
    }

}
