package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Todo {
    private long id;

    private String name;

    public Todo() {
        // Jackson deserialization
    }

    public Todo(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {return id; }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() { return name; }
}
