package test.todo.dto;

import lombok.Getter;
import lombok.Setter;
import test.todo.entity.Todo;

@Getter @Setter
public class TodoListResponse {

	private Long id;
	private String name;
	private Boolean completed;
	
	public TodoListResponse(Todo todo) {
		this.id = todo.getId();
		this.name = todo.getName();
		this.completed = todo.getCompleted();
	}
}
