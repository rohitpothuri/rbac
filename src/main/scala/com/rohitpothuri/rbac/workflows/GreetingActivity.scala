package com.rohitpothuri.rbac.workflows

import io.temporal.activity.{ActivityInterface, ActivityMethod}

@ActivityInterface trait GreetingActivity {
  @ActivityMethod def greet(name: String): String
}
