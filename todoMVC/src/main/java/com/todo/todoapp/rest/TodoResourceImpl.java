package com.todo.todoapp.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;
import com.todo.todoapp.data.TodoServiceDAO;
import org.hibernate.Session;
import com.todo.todoapp.data.QueryParameters;

import com.todo.todoapp.entities.Todo;
import com.todo.todoapp.entities.User;

public class TodoResourceImpl implements TodoResource {

	@Inject
	TodoServiceDAO todoDAO;
	
	@Inject
	Session session;
	 @GET
	 @Path("gettodos/{userId}")
	 @Produces("application/json")
	 public List<Todo> getTodos(@PathParam("userId") Long userId ){
		try{
			List<Todo> todoList =  todoDAO.findWithNamedQuery(Todo.BY_USER, QueryParameters.with("user", userId).parameters());
			return todoList;
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
	 }
	 
	 @POST
	 @Path("persist")
	 @Consumes("application/json")
	 public Response persist(Todo todo){
		 try{
			 todoDAO.create(todo);
			 return Response.ok().build();
		 }catch(Exception e){
			 throw new WebApplicationException(404);
		 }
	 }

	 @GET
	 @Path("gettodo/{userId}/{todoId}")
	 @Produces("application/json")
	 public Todo getTodo(@PathParam("userId") Long userId, @PathParam("todoTitle") String todoTitle){
		 try{
			 Todo todo = todoDAO.findUniqueWithNamedQuery(Todo.BY_TITLE_AND_USER, QueryParameters.with("title", todoTitle).and("user", userId).parameters());
			 return todo;
		 }catch(Exception e ){
			 throw new WebApplicationException(404);
		 }
	 }	
	 
	@DELETE
	@Path("{userId}/{todoId}")
	public Response delete(@PathParam("userId") Long userId, @PathParam("todoTitle") String todoTitle){
		try{
			Todo todo = todoDAO.findUniqueWithNamedQuery(Todo.BY_TITLE_AND_USER, QueryParameters.with("title", todoTitle).and("user", userId).parameters());
			todoDAO.delete(todo);
			return Response.ok().build();
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(Todo todo){
		try{
			todoDAO.update(todo);
			return Response.ok().build();
		}catch(Exception e){
			throw new WebApplicationException(400);
		}
	}
}
