package com.rohitpothuri.rbac.workflows

import io.temporal.spring.boot.WorkflowImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@WorkflowImpl(taskQueues = Array("GreetingTaskQueue"))
class GreetingWorkflowImpl(@Autowired greetingActivity: GreetingActivity) extends GreetingWorkflow {

  
  def execute(name: String): String = {
    val result = greetingActivity.greet(name)
    result
  }
}
