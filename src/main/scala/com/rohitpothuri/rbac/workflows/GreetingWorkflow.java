package com.rohitpothuri.rbac.workflows;

import io.temporal.workflow.Promise;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface GreetingWorkflow {

    @WorkflowMethod
    String execute(String name);

   /* @WorkflowMethod
    Promise<String> executeAsync(String name);*/
}