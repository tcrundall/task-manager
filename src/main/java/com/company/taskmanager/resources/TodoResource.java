package com.company.taskmanager.resources;

import com.company.taskmanager.api.DraftTodo;
import com.company.taskmanager.api.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private HashMap<Long, Todo> todos;
    private final AtomicLong counter;

    public TodoResource() {
        counter = new AtomicLong();

        // Populate fresh hash with some dummy todos
        todos = new HashMap<>();
        for (int i=0; i<3; i++) {
            long id = counter.incrementAndGet();
            todos.put(id, new Todo(id, "misc "+id, Optional.empty()));
        }
    }

    //    GET /todos → Returns a list of all Todos
    @GET
    public Collection<Todo> getTodos() {
        return todos.values();
    }

    //    GET /todos/{id} → Returns a Todo
    @GET
    @Path("{id}")
    public Todo getTodo(@PathParam("id") long id) {
        return todos.get(id);
    }

    //    POST /todos → Expects a Todo (without id) and returns a Todo with id
    @POST
    public Todo postTodo(DraftTodo draftTodo) {
        long new_id = counter.incrementAndGet();
        Todo todo = new Todo(new_id, draftTodo);
        todos.put(new_id, todo);
        return todo;
    }

    //    PUT /todos/{id} → Overwrites an existing Todo
    @PUT
    @Path("{id}")
    public String putTodo(@PathParam("id") long id, DraftTodo draftTodo) {
        Todo todo = new Todo(id, draftTodo);
        todos.put(id, todo);
        return "Successful put!";
    }

    //    DELETE /todos/{id} → Deletes a Todo
    @DELETE
    @Path("{id}")
    public void deleteTodo(@PathParam("id") long id) {
        todos.remove(id);
    }

}
