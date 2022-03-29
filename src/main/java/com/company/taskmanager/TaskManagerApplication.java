package com.company.taskmanager;

import com.company.taskmanager.health.TodoHealthCheck;
import com.company.taskmanager.resources.TodoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * A toy application for managing tasks / demonstrating usage of Dropwizard
 * This application has a single resource: todoResource.
 * This resource keeps track of a set of Todo objects, stored in a hashmap.
 * Each Todo object has a list of associated tasks.
 * A client can use RESTful methods to interact with the set of Todos.
 */
public class TaskManagerApplication extends Application<TaskManagerConfiguration> {
    public static void main(String[] args) throws Exception {
        new TaskManagerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TaskManagerConfiguration> bootstrap) {
        // nothing to do yet
    }

    /**
     * Dropwizard boilerplate method for running the application
     * In this method the various resources and healthchecks are
     * initialised and registered.
     */
    @Override
    public void run(TaskManagerConfiguration configuration,
                    Environment environment) {

        // Add resources
        final TodoResource todoResource = new TodoResource();
        environment.jersey().register(todoResource);

        // Add health checks
        final TodoHealthCheck todoHealthCheck =
                new TodoHealthCheck(todoResource.getTodoHashmap());
        environment.healthChecks().register("todo", todoHealthCheck);
    }

}