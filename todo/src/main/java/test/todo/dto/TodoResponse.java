package test.todo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import test.todo.entity.Todo;

@Getter @Setter
public class TodoResponse {

	private Long id;
	private String name;
	private Boolean completed;
	private LocalDateTime completedAt;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	
	public TodoResponse(Todo todo) {
		this.id = todo.getId();
		this.name = todo.getName();
		this.completed = todo.getCompleted();
		this.completedAt = todo.getCompletedAt();
		this.createdAt = todo.getCreatedAt();
		this.updateAt = todo.getUpdateAt();
	}
}
