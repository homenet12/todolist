package test.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import test.todo.dto.TodoRequest;
import test.todo.dto.TodoResponse;
import test.todo.service.TodoService;

@RestController
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	
	@GetMapping("/todos")
	public ResponseEntity<List<TodoResponse>> todos(){
		return ResponseEntity.ok(todoService.todoAll());
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<TodoResponse> getTodo(@PathVariable Long id){
		return ResponseEntity.ok(todoService.todoGetOne(id));
	}
	
	@PostMapping("/todos")
	public ResponseEntity<TodoResponse> saveTodo(@RequestBody TodoRequest.createRequest todoRequest){
		return ResponseEntity.ok(todoService.saveTodo(todoRequest));
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<TodoResponse> updateTodo(TodoRequest.updateRequest todoRequest){
		return ResponseEntity.ok(todoService.updateTodo(todoRequest));
	}
	
	@DeleteMapping("/todos/{id}")
	public void deleteTodo(Long id){
		todoService.deleteTodo(id);
	}
}
