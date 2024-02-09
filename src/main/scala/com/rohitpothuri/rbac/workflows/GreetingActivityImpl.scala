package com.rohitpothuri.rbac.workflows

import io.temporal.spring.boot.ActivityImpl
import org.springframework.stereotype.Service

@ActivityImpl(taskQueues = Array("GreetingTaskQueue"))
class GreetingActivityImpl extends GreetingActivity {
  def greet(name: String): String = "Hello, " + name + "!"
}
