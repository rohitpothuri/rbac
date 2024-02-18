package com.rohitpothuri.rbac.workflows

import io.temporal.activity.Activity
import io.temporal.spring.boot.ActivityImpl

/** The InitialInterval property defines how long after the initial failure the
  * first retry will occur. By default, that's one second. The
  * BackoffCoefficient is a multiplier, applied to the InitialInterval value,
  * that's used to calculate the delay between each subsequent attempt. Assuming
  * that you use the defaults for both properties, that means there will be a
  * retry after 1 second, another after 2 seconds, then 4 seconds, 8 seconds and
  * so on. The MaximumInterval puts a limit on that delay, and by default it's
  * 100 times the initial interval, which means that the delays would keep
  * doubling as described, but would never exceed 100 seconds. Finally, the
  * MaximumAttempts specified the maximum count of retries allowed before
  * marking the Activity as failed, in which case the Workflow can handle the
  * failure according to its business logic. The image below shows an annotated
  * example of a RetryPolicy. There are three steps to using a custom
  * RetryPolicy to control how Activity failure is handled in your Workflow:
  *
  * Import the io.temporal.common.RetryOptions; package Specify values for one
  * or more properties, such as InitialInterval or BackoffCoefficient, described
  * on the previous page Associate your policy with the ActivityOptions used
  * with your Activity
  */
@ActivityImpl(taskQueues = Array("GreetingTaskQueue"))
class GreetingActivityImpl extends GreetingActivity {
  override def greet(name: String): String = try
    // throw new Exception("This is a test exception");
    // whenever there is an exception temporal is taking care of retrying the activity. which is great
    "Hello, " + name + "!"
  catch {
    case e: Exception =>
      // this is the way to wrap the exception to show the stack trace on temporal web ui
      throw Activity.wrap(e)
  }
}
