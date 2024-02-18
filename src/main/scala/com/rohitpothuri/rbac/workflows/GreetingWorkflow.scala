package com.rohitpothuri.rbac.workflows

import io.temporal.workflow.{WorkflowInterface, WorkflowMethod}

@WorkflowInterface
trait GreetingWorkflow {
  @WorkflowMethod def execute(name: String): String
  /* @WorkflowMethod
      Promise<String> executeAsync(String name);*/
}
