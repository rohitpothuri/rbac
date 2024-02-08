package com.rohitpothuri.rbac.common.workflows

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface 
trait GreetingActivity {
  @ActivityMethod def greet(name: String): String
}
