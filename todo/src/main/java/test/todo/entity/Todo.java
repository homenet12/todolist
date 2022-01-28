package test.todo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@Column(name = "todo_id")
	private Long id;
	
	private String name;
	private Boolean completed;
	
	private LocalDateTime completedAt;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	protected Todo(String name) {
		this.name = name;
		this.completed = null;
		this.completedAt = null;
		this.createdAt = LocalDateTime.now();
		this.updateAt = LocalDateTime.now();
	}
	
	public static Todo createTodo(String name) {
		return new Todo(name);
	}
	
	public void update(TodoRequest.UpdateRequest todoRequest) {
		setName(todoRequest.getName());
		if(todoRequest.getCompleted() != null && todoRequest.getCompleted()) {
			complete();
		}
		this.updateAt = LocalDateTime.now();
	}
	
	public void setName(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
	}
	
	public void complete() {
		if(!this.completed) {
			this.completed = true;
			this.completedAt = LocalDateTime.now();
		}
	}
}
