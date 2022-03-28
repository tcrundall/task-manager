package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Task {
    private long id;
    private String name;
    private Optional<String> description;

    public Task() {
        // Jackson deserialization
    }

    public Task(long id, String name, Optional<String> description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @JsonProperty
    public long getId() {return id; }

    @JsonProperty
    public String getName() { return name; }

    @JsonProperty
    public Optional<String> getDescription() {return description; }
}
