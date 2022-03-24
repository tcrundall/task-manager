package com.company.taskmanager.health;

import com.codahale.metrics.health.HealthCheck;

public class TodoHealthCheck extends HealthCheck {
    public TodoHealthCheck() {}

    @Override
    protected Result check() throws Exception {
        if (true) {
            return Result.healthy("Testing a success");
        }
        return Result.unhealthy("something went wrong...");
    }
}
