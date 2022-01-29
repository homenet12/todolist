package test.todo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test.todo.common.FileHelper;
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
	
	@Column(length = 255)
	private String imgUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	protected Todo(TodoRequest.CreateRequest todoRequest) {
		this.name = todoRequest.getName();
		this.completed = null;
		this.completedAt = null;
		this.createdAt = LocalDateTime.now();
		this.updateAt = LocalDateTime.now();
		imgUpload(todoRequest.getFile());
	}
	
	public static Todo createTodo(TodoRequest.CreateRequest todoRequest) {
		return new Todo(todoRequest);
	}
	
	public void update(TodoRequest.UpdateRequest todoRequest) {
		setName(todoRequest.getName());
		if(todoRequest.getCompleted() != null && todoRequest.getCompleted()) {
			complete();
		}
		imgUpload(todoRequest.getFile());
		this.updateAt = LocalDateTime.now();
	}
	
	private void imgUpload(MultipartFile img) {
		if(!img.isEmpty()) {
			this.imgUrl = FileHelper.uploadImg(img);
		}
	}
	
	private void setName(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
	}
	
	private void complete() {
		if(!this.completed) {
			this.completed = true;
			this.completedAt = LocalDateTime.now();
		}
	}

	public void deleteImg() {
		if(this.imgUrl != null) {
			FileHelper.deleteImg(this.imgUrl);
			this.imgUrl = null;
		}
	}
}
