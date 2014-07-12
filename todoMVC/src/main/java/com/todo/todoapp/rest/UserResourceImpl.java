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
		List<User> userList = session.createCriteria(User.class).list();
		if(userList != null){
			return userList;
		}else{
			return null;
		}
	}

	@POST
	@Path("persist")
	@Consumes("application/json")
	public Response persist(User user) {
		userDAO.create(user);
		// TODO Auto-generated method stub
		return Response.ok().build();
	}

	@GET
	@Path("getuser/{id}")
	@Produces("application/json")
	public User getUser(@PathParam("id") Long id) {
		User user = userDAO.getUserById(id) ;
		if(user != null){
			return user;
		}else{
			return null;
		}
	}

}
