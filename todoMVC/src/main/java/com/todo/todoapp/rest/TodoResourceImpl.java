package com.todo.todoapp.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;
import com.todo.todoapp.data.TodoServiceDAO;
import org.hibernate.Session;
import com.todo.todoapp.data.QueryParameters;

import com.todo.todoapp.entities.Todo;

public class TodoResourceImpl implements TodoResource {

	@Inject
	TodoServiceDAO todoDAO;
	
	@Inject
	Session session;
	 @GET
	 @Path("gettodos/{userId}")
	 @Produces("application/json")
	 public List<Todo> getTodos(@PathParam("userId") Long userId ){
		List<Todo> todoList =  todoDAO.findWithNamedQuery(Todo.BY_USER, QueryParameters.with("user", userId).parameters());
		return todoList;
	 }
	 
	 @POST
	 @Path("persist")
	 @Consumes("application/json")
	 public Response persist(Todo todo){
		 todoDAO.create(todo);
		 return Response.ok().build();
	 }

	 @GET
	 @Path("gettodo/{userId}/{todoId}")
	 @Produces("application/json")
	 public Todo getTodo(@PathParam("userId") Long userId, @PathParam("todoTitle") String todoTitle){
		 Todo todo = todoDAO.findUniqueWithNamedQuery(Todo.BY_TITLE_AND_USER, QueryParameters.with("title", todoTitle).and("user", userId).parameters());
		 return todo;
	 }	
}
