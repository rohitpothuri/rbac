package com.rohitpothuri.rbac.workflows;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GreetingWorker {
    @PostConstruct
    public void start() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        // Create a worker that listens to the task queue
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("GreetingsTaskQueue");
        // Register workflow and activity implementations
        worker.registerWorkflowImplementationTypes(GreetingWorkflow.class);
        worker.registerActivitiesImplementations(new GreetingActivityImpl());
        // Start listening to the task queue
        factory.start();
    }
}
