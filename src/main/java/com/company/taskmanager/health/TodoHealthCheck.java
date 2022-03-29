package com.company.taskmanager.health;

import com.codahale.metrics.health.HealthCheck;
import com.company.taskmanager.api.Task;
import com.company.taskmanager.api.Todo;

import java.util.Collection;
import java.util.HashMap;

/**
 * A Healthcheck for the TodoResource
 */
public class TodoHealthCheck extends HealthCheck {
    private HashMap<Long, Todo> todos;

    public TodoHealthCheck(HashMap<Long, Todo> todos) {
        this.todos = todos;
    }

    /**
     * Checks the health of the Hashmap of todos
     * Ensures that all names and ids of all todos and tasks
     * are valid.
     * Validity is defined as:
     *   id >= 0
     *   name != ""
     */
    @Override
    protected Result check() throws Exception {
        System.out.println(todos.values());
        for (Todo t : todos.values()) {
            if (t.getId() < 0) {
                return Result.unhealthy("A todo has an invalid or missing id (" + t.getId() +")");
            }
            if (t.getName() == "") {
                return Result.unhealthy("Todo " + t.getId() + " has empty string for name");
            }
            for (Task task : t.getTasks()) {
                if (task.getId() < 0) {
                    return Result.unhealthy("Task " + task.getName() + " of todo "
                    + t.getId() + " has invalid or missing id (" + task.getId() +")");
                }
                if (task.getName() == "") {
                    return Result.unhealthy("Task " + task.getId() + " of todo " + t.getId()
                            + "has empty string for name");
                }
            }
        }

        return Result.healthy("All ids and names for all todos and tasks are valid");
    }
}
