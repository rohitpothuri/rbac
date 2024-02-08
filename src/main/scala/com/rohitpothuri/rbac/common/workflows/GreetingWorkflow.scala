package com.rohitpothuri.rbac.common.workflows

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface trait GreetingWorkflow {
  @WorkflowMethod def execute(name: String): Unit
}
