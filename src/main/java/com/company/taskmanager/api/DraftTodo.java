package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Optional;

public class DraftTodo {
    private String name;
    private ArrayList<Task> tasks;

    public DraftTodo() {
        // Jackson deserialization
    }

    public DraftTodo(String name) {
        this.name = name;
        tasks = new ArrayList<>();
        for (long i=0;i<3;i++) {
            tasks.add(new Task(i, "dummy task "+i, Optional.of("dummy description")));
        }
    }

    @JsonProperty
    public String getName() { return name; }

    @JsonProperty
    public ArrayList<Task> getTasks() { return tasks; }
}
