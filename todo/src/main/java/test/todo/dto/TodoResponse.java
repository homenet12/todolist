package test.todo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import test.todo.entity.Todo;

@Getter @Setter
public class TodoResponse extends TodoListResponse{

	private LocalDateTime completedAt;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	
	public TodoResponse(Todo todo) {
		super(todo);
		this.completedAt = todo.getCompletedAt();
		this.createdAt = todo.getCreatedAt();
		this.updateAt = todo.getUpdateAt();
	}
}
