package com.company.taskmanager.resources;

import com.company.taskmanager.api.DraftTodo;
import com.company.taskmanager.api.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is a RESTful resource which manages a set of
 * todo objects.
 *
 * The todos are stored in a hashmap. The keys for the
 * hashmap are the ids of the todos. To ensure uniqueness
 * of keys/ids they are generated within the TodoResource
 * via an AtomicLong.
 */
@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private HashMap<Long, Todo> todos;
    private final AtomicLong counter;

    /**
     * Creates a TodoResource
     * Initialises an AtomicLong counter which will be used
     * to generate unique todo ids.
     * Initalises also the hashmap of todos, and populates it
     * with some simple todo objects.
     */
    public TodoResource() {
        counter = new AtomicLong();

        // Populate fresh hash with some dummy todos
        todos = new HashMap<>();
        for (int i=0; i<3; i++) {
            long id = counter.incrementAndGet();
            todos.put(id, new Todo(id, "misc "+id, Optional.empty()));
        }
    }

    public HashMap<Long, Todo> getTodoHashmap() {
        return todos;
    }

    /**
     * GET /todos
     * returns a collection of all todos
     */
    @GET
    public Collection<Todo> getTodos() {
        return todos.values();
    }

    /**
     * GET /todos/{id}
     * Returns a todo with the associated id
     */
    @GET
    @Path("{id}")
    public Todo getTodo(@PathParam("id") long id) {
        return todos.get(id);
    }

    /**
     * POST /todos
     * Expects a draftTodo (similar to a Todo but without an id)
     * and returns a Todo with identical attributes to draftTodo but
     * with a newly generated id
     */
    @POST
    public Todo postTodo(DraftTodo draftTodo) {
        long new_id = counter.incrementAndGet();
        Todo todo = new Todo(new_id, draftTodo);
        todos.put(new_id, todo);
        return todo;
    }

    /**
     * PUT /todos/{id}
     * Takes a draftTodo and overwrites an existing Todo
     */
    @PUT
    @Path("{id}")
    public String putTodo(@PathParam("id") long id, DraftTodo draftTodo) {
        Todo todo = new Todo(id, draftTodo);
        todos.put(id, todo);
        return "Successful put!";
    }

    /**
     * DELETE /todos/{id}
     * Deletes a Todo with the associated id
     */
    @DELETE
    @Path("{id}")
    public void deleteTodo(@PathParam("id") long id) {
        todos.remove(id);
    }

}
