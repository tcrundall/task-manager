package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * Represents a Task
 * Tasks have an id, name and optional description.
 * Since tasks are always provided along with a (Draft)Todo object,
 * uniqueness of task ids is not enforced.
 *
 * Jackson annotations are used to allow for (de)serialization between
 * JSON and java
 */
public class Task {
    private long id;
    private String name;
    private Optional<String> description;

    public Task() {
        // Jackson deserialization
    }

    /**
     * Creates a task
     * @param id            id number
     * @param name          name of task
     * @param description   optional description of task
     */
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
