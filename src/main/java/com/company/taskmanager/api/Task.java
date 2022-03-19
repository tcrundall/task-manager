package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    private long id;
    private String name;

    public Task() {
        // Jackson deserialization
    }

    public Task(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {return id; }

    @JsonProperty
    public String getName() { return name; }
}
