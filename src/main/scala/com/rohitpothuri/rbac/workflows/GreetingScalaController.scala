package com.rohitpothuri.rbac.workflows

import io.temporal.client.{WorkflowClient, WorkflowOptions, WorkflowStub}
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import io.temporal.workflow.Promise
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

/** Namespaces can be used for envs or for separating conncerns workers need to
  * be restarted whenever there is a change in the workflow code
  */
@Controller
class GreetingScalaController(@Autowired val client: WorkflowClient) {
  @PutMapping(value = Array("/"))
  def helloSample: ResponseEntity[String] = {
    val workflow = client.newWorkflowStub(
      classOf[GreetingWorkflow],
      WorkflowOptions.newBuilder
        .setTaskQueue("GreetingTaskQueue")
        .setWorkflowId("Greeting")
        .build
    )
    val greeting = workflow.execute("Rohit")
    val workflowDetails = WorkflowStub.fromTyped(workflow).getExecution
    System.out.println(
      workflowDetails.getWorkflowId + "|" + workflowDetails.getRunId + " " + greeting
    )
    new ResponseEntity[String](
      workflowDetails.getWorkflowId + "|" + workflowDetails.getRunId + " " + greeting,
      HttpStatus.OK
    )
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
  @PutMapping(value =
    Array("/startWorker")
  ) def startWorker: ResponseEntity[String] = {
    // f you are using Temporal Cloud, you would instead call the newServiceStubs(WorkflowServiceStubsOptions options)
    // method using options that include the hostname and port number used to reach its frontend service.
    val service = WorkflowServiceStubs.newLocalServiceStubs
    val client = WorkflowClient.newInstance(service)
    val factory = WorkerFactory.newInstance(client)
    // Specify the name of the Task Queue that this Worker should poll
    val worker = factory.newWorker("GreetingTaskQueue")
    // Specify which Workflow implementations this Worker will support
    worker.registerWorkflowImplementationTypes(classOf[GreetingWorkflowImpl])
    worker.registerActivitiesImplementations(new GreetingActivityImpl)
    // Begin running the Worker
    factory.start()
    new ResponseEntity[String]("Worker started", HttpStatus.OK)
  }
}
