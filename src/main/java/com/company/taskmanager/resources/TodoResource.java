package com.company.taskmanager.resources;

import com.company.taskmanager.api.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private HashMap<Long, Todo> todos = new HashMap();
    private final AtomicLong counter;

    public TodoResource() {
        counter = new AtomicLong();

        for (int i=0; i<10; i++) {
            long id = counter.incrementAndGet();
            todos.put(id, new Todo(id, "misc "+id));
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
    public Todo postTodo(Todo in_todo) {
        long new_id = counter.incrementAndGet();
        Todo todo = new Todo(new_id, in_todo.getName());
        todos.put(new_id, todo);
        return todo;
    }

    //    PUT /todos/{id} → Overwrites an existing Todo
    @PUT
    @Path("{id}")
    public String putTodo(@PathParam("id") long id, @QueryParam("name") Optional<String> name, Todo in_todo) {
        if (id == in_todo.getId()) {
            todos.put(id, in_todo);
            return "Successful put!";
        }
        in_todo.setId(id);
        todos.put(id, in_todo);
        return "Inconsistent ids";

    }

    //    DELETE /todos/{id} → Deletes a Todo
    @DELETE
    @Path("{id}")
    public void deleteTodo(@PathParam("id") long id) {
        todos.remove(id);
    }

}
