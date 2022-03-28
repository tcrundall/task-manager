package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Optional;

public class Todo {
    private long id;
    private String name;
    private Optional<String> description;
    private ArrayList<Task> tasks;

    public Todo() {
        // Jackson deserialization
    }

    public Todo(long id, DraftTodo draftTodo) {
        this.id = id;
        this.name = draftTodo.getName();
        this.description = draftTodo.getDescription();
        this.tasks = draftTodo.getTasks();
    }

    public Todo(long id, String name) {
        this.id = id;
        this.name = name;
        tasks = new ArrayList<>();
        for (long i=0;i<3;i++) {
            tasks.add(new Task(i, "dummy task "+i, Optional.of("dummy description")));
        }
    }

    @JsonProperty
    public long getId() {return id; }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() { return name; }

    @JsonProperty
    public Optional<String> getDescription() { return description; }

    @JsonProperty
    public ArrayList<Task> getTasks() { return tasks; }
}
