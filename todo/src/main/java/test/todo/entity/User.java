package test.todo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private int age;
	
	@OneToMany(mappedBy = "user")
	private List<Todo> todos = new ArrayList<>();
}
