package com.todo.todoapp.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import org.apache.tapestry5.ioc.annotations.Inject;
import com.todo.todoapp.data.UserServiceDAO;
import org.hibernate.Session;
import com.todo.todoapp.entities.User;

public class UserResourceImpl implements UserResource {
	
	@Inject
	UserServiceDAO userDAO;
	
	@Inject
	Session session;
	
	@GET
	@Produces("application/json")
	public List<User> getUsers() {
		try{
			List<User> userList = session.createCriteria(User.class).list();
			return userList;
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
	}

	@POST
	@Path("persist")
	@Consumes("application/json")
	public Response persist(User user) {
		try{
			userDAO.create(user);
			return Response.ok().build();
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
	}

	@GET
	@Path("getuser/{id}")
	@Produces("application/json")
	public User getUser(@PathParam("id") Long id) {
		try{
			User user = userDAO.getUserById(id) ;
			return user;
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id){
		try{
			User user = userDAO.getUserById(id);
			userDAO.delete(user);
			return Response.ok().build();
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(User user){
		try{
			userDAO.update(user);
			return Response.ok().build();
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
	}
}
