package com.todo.todoapp.pages;


import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.todo.todoapp.entities.User;

public class AdminLogin {

		
		@Property
		private String password;
		
		@Inject
		private Messages messages;
		
		@Property
		private String error;
		
		@SessionState
		private User loggedUser;
		
		@Property
		@Component
		private Form loginForm;
		
		@OnEvent(value = "validate", component = "loginForm")
		public void validateLoginForm(){
			String realPassword = "secret";
			if( !(password.equals(realPassword)) ){
				loginForm.recordError(messages.get("login.erro.global.invalid"));
			}
		}
		
		@OnEvent(value = "success", component = "loginForm")
		public Object onSuccess(){
			loggedUser = new User();
			return AdminPanel.class;
		}
		
	
	
}
