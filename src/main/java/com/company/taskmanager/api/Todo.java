package com.company.taskmanager.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents a Todo item.
 * Todos are created solely by the server. This is done
 * to ensure correct assignment of unique IDs.
 * Todos can be created either from a draftTodo object
 * or from just a name.
 *
 * Jackson annotations are used to allow for (de)serialization between
 * JSON and java
 */
public class Todo {
    private long id;
    private String name;
    private Optional<String> description;
    private ArrayList<Task> tasks;

    public Todo() {
        // Jackson deserialization
    }

    /**
     * Creates a Todo object from a draftTodo object
     * resulting in a Todo object with an id, a name
     * an optional description, and a arraylist of tasks.
     * @param id        The id number of the Todo object
     *                  This is assumed to be unique.
     * @param draftTodo The draftTodo which will be cloned.
     */
    public Todo(long id, DraftTodo draftTodo) {
        this.id = id;
        this.name = draftTodo.getName();
        this.description = draftTodo.getDescription();
        this.tasks = draftTodo.getTasks();
    }

    /**
     * Creates a todo with given name and an optional description.
     * For testing purposes we auto-generate a list of dummy tasks
     * @param name          The name of the draftTodo.
     *                      Ideally short and simple. Spaces are permitted
     * @param description   The description of the draftTodo. As long as necessary.
     */
    public Todo(long id, String name, Optional<String> description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
