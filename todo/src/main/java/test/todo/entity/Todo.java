package test.todo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test.todo.dto.TodoRequest;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private Boolean completed;
	
	private LocalDateTime completedAt;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	
	protected Todo(String name) {
		this.name = name;
		this.completed = false;
		this.completedAt = null;
		this.createdAt = LocalDateTime.now();
		this.updateAt = LocalDateTime.now();
	}
	
	public static Todo createTodo(String name) {
		return new Todo(name);
	}
	
	public void update(TodoRequest.updateRequest todoRequest ) {
		this.setName(todoRequest.getName());
		this.complete(todoRequest.getCompleted());
		this.updateAt = LocalDateTime.now();
	}
	
	public void setName(String name) {
		if(!name.isEmpty() && name != null) {
			this.name = name;
		}
	}
	
	public void complete(Boolean completed) {
		if(completed) {
			this.completed = true;
			this.completedAt = LocalDateTime.now();
		}
	}
}
