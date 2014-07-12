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

import com.todo.todoapp.data.QueryParameters;
import com.todo.todoapp.entities.Todo;
import com.todo.todoapp.entities.User;

@Path("/todo")
public interface TodoResource {
	
	 @GET
	 @Path("gettodos/{userId}")
	 @Produces("application/json")
	 List<Todo> getTodos(@PathParam("userId") Long userId );
	 
	 @POST
	 @Path("persist")
	 @Consumes("application/json")
	 Response persist(Todo todo);

	 @GET
	 @Path("gettodo/{userId}/{todoTitle}")
	 @Produces("application/json")
	 Todo getTodo(@PathParam("userId") Long userId, @PathParam("todoTitle") String todoTitle);
	 
	 @DELETE
	 @Path("{userId}/{todoId}")
	 public Response delete(@PathParam("userId") Long userId, @PathParam("todoTitle") String todoTitle);
	
	 @PUT
	 @Consumes("application/json")
	 public Response update(Todo todo);

}
