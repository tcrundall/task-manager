package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

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
            tasks.add(new Task(i, "dummy task "+i));
        }
    }

    @JsonProperty
    public String getName() { return name; }

    @JsonProperty
    public ArrayList<Task> getTasks() { return tasks; }

    public void setName(String name) {
        this.name = name;
    }
}
