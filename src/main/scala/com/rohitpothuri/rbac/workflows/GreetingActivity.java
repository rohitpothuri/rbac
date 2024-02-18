package com.rohitpothuri.rbac.workflows;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface GreetingActivity {
    @ActivityMethod
    String greet(String name);
}
