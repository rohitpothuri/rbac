package com.rohitpothuri.rbac.workflows

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface 
trait GreetingWorkflow {
  @WorkflowMethod 
  def execute(name: String): String
}
