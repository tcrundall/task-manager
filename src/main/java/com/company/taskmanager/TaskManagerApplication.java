package com.company.taskmanager;

import com.company.taskmanager.health.TemplateHealthCheck;
import com.company.taskmanager.health.TodoHealthCheck;
import com.company.taskmanager.resources.TaskManagerResource;
import com.company.taskmanager.resources.TodoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TaskManagerApplication extends Application<TaskManagerConfiguration> {
    public static void main(String[] args) throws Exception {
        new TaskManagerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<TaskManagerConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(TaskManagerConfiguration configuration,
                    Environment environment) {

        // Add resources
        final TaskManagerResource resource = new TaskManagerResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TodoResource todoResource = new TodoResource();
        environment.jersey().register(todoResource);

        // Add health checks
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        final TodoHealthCheck todoHealthCheck =
                new TodoHealthCheck(todoResource.getTodos());
        environment.healthChecks().register("todo", todoHealthCheck);
    }

}