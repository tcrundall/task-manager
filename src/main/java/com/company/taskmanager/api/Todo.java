package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Todo {
    private long id;
    private String name;
    private ArrayList<Task> tasks;

    public Todo() {
        // Jackson deserialization
    }

    public Todo(long id, String name) {
        this.id = id;
        this.name = name;
        tasks = new ArrayList<>();
        for (long i=0;i<3;i++) {
            tasks.add(new Task(i, "dummy task "+i));
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
    public ArrayList<Task> getTasks() { return tasks; }
}
