package com.company.taskmanager.resources;

import com.codahale.metrics.annotation.Timed;
import com.company.taskmanager.api.Todo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private HashMap<Long, Todo> todos = new HashMap();

    public TodoResource() {
        for (long i=0; i<10; i++) {
            todos.put(i, new Todo(i, "misc"));
        }
    }

    //    GET /todos → Returns a list of all Todos
    @GET
    public HashMap<Long,Todo> getTodos() {
        return todos;
    }

    //    GET /todos/{id} → Returns a Todo
    @GET
    @Path("{id}")
    public Todo getTodo(@PathParam("id") long id) {
        return todos.get(id);
    }

    //    POST /todos → Expects a Todo (without id) and returns a Todo with id
    @POST
    public Todo postTodo(Todo in_todo) {
        long new_id = Collections.max(todos.keySet()) + 1;
        Todo todo = new Todo(new_id, in_todo.getName());
        todos.put(new_id, todo);
        return todo;
    }

    //    PUT /todos/{id} → Overwrites an existing Todo
    @PUT
    @Path("{id}")
    public void putTodo(@PathParam("id") long id, Todo in_todo) {
        todos.put(id, in_todo);
    }

    //    DELETE /todos/{id} → Deletes a Todo
    @DELETE
    @Path("{id}")
    public void deleteTodo(@PathParam("id") long id) {
        todos.remove(id);
    }

}
