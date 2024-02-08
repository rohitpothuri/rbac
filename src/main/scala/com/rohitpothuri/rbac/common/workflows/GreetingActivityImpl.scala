package com.rohitpothuri.rbac.common.workflows

@Service class GreetingActivityImpl extends GreetingActivity {
  def greet(name: String): String = "Hello, " + name + "!"
}
