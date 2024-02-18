package com.rohitpothuri.rbac.workflows

import io.temporal.activity.ActivityOptions
import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow

import java.time.Duration

@WorkflowImpl(taskQueues = Array("GreetingTaskQueue"))
class GreetingWorkflowImpl extends GreetingWorkflow {
  private[workflows] val options = ActivityOptions.newBuilder
    .setStartToCloseTimeout(Duration.ofSeconds(5))
    .build
  final private val activities =
    Workflow.newActivityStub(classOf[GreetingActivity], options)

  override def execute(name: String): String = activities.greet(name)

  /*@Override
      public Promise<String> executeAsync(String name) {
          Promise<String> hello = Async.function(activities::greet, name);
          return hello;
      }*/
}
