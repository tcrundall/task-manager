package com.company.taskmanager.health;

import com.codahale.metrics.health.HealthCheck;
import com.company.taskmanager.api.Task;
import com.company.taskmanager.api.Todo;

import java.util.Collection;

public class TodoHealthCheck extends HealthCheck {
    private Collection<Todo> todos;

    public TodoHealthCheck(Collection<Todo> todos) {
        this.todos = todos;
    }

    @Override
    protected Result check() throws Exception {
        for (Todo t : todos) {
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
