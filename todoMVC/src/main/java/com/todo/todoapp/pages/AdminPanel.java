package com.todo.todoapp.pages;
import com.todo.todoapp.entities.*;
import com.todo.todoapp.pages.Index;

import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.hibernate.annotations.*;



public class AdminPanel {
	
	@Inject
	Session session;
	
	@SessionState
	private User loggedUser;
	
	
	Object onWipedb(){
		wipeDatabase("User");
		wipeDatabase("Todo");
		loggedUser = null;
		return Index.class;
	}
	
	@CommitAfter
	void wipeDatabase(String tableName){
		
		String hql = String.format( "delete from %s",tableName);
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}
}
