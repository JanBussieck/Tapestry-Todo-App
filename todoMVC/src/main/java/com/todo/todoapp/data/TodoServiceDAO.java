package com.todo.todoapp.data;


import com.todo.todoapp.entities.Todo;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

public interface TodoServiceDAO {
	
	@CommitAfter
	public Todo create(final Todo todo);
	
	@CommitAfter
	public Todo update(Todo todo);
	
	@CommitAfter
	public void delete(final Todo todo);
	
	public Todo getTodoById(long id);
	
	public Todo findUniqueWithNamedQuery(String queryName, Map<String, Object> params);
	
	public Todo findUniqueWithNamedQuery(String queryName);
	
	public List<Todo> findWithNamedQuery(String queryName, Map<String, Object> params);
}