package com.todo.todoapp.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Path("getuser/{id}")
	@Produces("application/json")
	public User getUser(@PathParam("id") Long id);
}
