package com.rohitpothuri.rbac.common.workflows

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service 
class GreetingWorkflowImpl(@Autowired greetingActivity: GreetingActivity) extends GreetingWorkflow {

  def execute(name: String): Unit = {
    val result = greetingActivity.greet(name)
    println(result)
  }
}
