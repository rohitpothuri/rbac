package com.rohitpothuri.rbac.workflows;

import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

@WorkflowImpl(taskQueues = "GreetingTaskQueue")
public class GreetingWorkflowImpl implements GreetingWorkflow {
    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build();

    private final GreetingActivity activities =
            Workflow.newActivityStub(GreetingActivity.class, options);
    @Override
    public String execute(String name) {
        return activities.greet(name);
    }

    /*@Override
    public Promise<String> executeAsync(String name) {
        Promise<String> hello = Async.function(activities::greet, name);
        return hello;
    }*/
}
