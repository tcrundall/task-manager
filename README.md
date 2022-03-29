# Task Manager Application

## Running application
Build the application by running the following in projects root directory:

`>> mvn package`

Then run the application by executing

`>> java -jar target/task-manager-1.0-SNAPSHOT.jar server`

## Behaviour
The default location is `https://localhost:8080` for clients 
and `https://localhost:8081` for admins.

The service supports the following endpoints:
- GET /todos → Returns a list of all Todos
- POST /todos → Expects a Todo (without id) and returns a Todo with id
- GET /todos/{id} → Returns a Todo
- PUT /todos/{id} → Overwrites an existing Todo
- DELETE /todos/{id} → Deletes a Todo

Healthchecks can be run over the admin port with:
- GET /healthcheck

## Examples

### Put
PUT http://localhost:8080/todos/1

Body

`{
"name": "mytodo",
"description": "this is very descriptive",
"tasks": [
{
"id": 1,
"name": "My task"
},
]
}`

## Notes

