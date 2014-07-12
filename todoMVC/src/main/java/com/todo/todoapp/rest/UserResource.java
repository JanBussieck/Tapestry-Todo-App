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
import com.todo.todoapp.entities.User;

@Path("/user")
public interface UserResource {
	
	@GET
	@Produces("application/json")
	public List<User> getUsers();

	@POST
	@Path("persist")
	@Consumes("application/json")
	public Response persist(User user);

	@GET
	@Path("getuser/{email}")
	@Produces("application/json")
	public User getUser(@PathParam("email") String email);
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id);
	
	@PUT
	@Consumes("application/json")
	public Response update(User user);
		
}
