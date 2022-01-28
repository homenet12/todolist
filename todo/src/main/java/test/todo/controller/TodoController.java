package test.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import test.todo.dto.TodoListResponse;
import test.todo.dto.TodoRequest;
import test.todo.dto.TodoResponse;
import test.todo.service.TodoService;

@RestController
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	
	@GetMapping("/todos")
	@ResponseStatus(value = HttpStatus.OK)
	public List<TodoListResponse> todos(@RequestBody @Valid TodoRequest.Search search){
		return todoService.todoAll(search);
	}
	
	@GetMapping("/todos/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public TodoResponse getTodo(@PathVariable Long id){
		return todoService.todoGetOne(id);
	}
	
	@PostMapping("/todos")
	@ResponseStatus(value = HttpStatus.CREATED)
	public TodoResponse saveTodo(@RequestBody @Valid TodoRequest.CreateRequest todoRequest){
		return todoService.saveTodo(todoRequest);
	}
	
	@PutMapping("/todos/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public TodoResponse updateTodo(@PathVariable Long id, @RequestBody TodoRequest.UpdateRequest todoRequest){
		return todoService.updateTodo(id, todoRequest);
	}
	
	@DeleteMapping("/todos/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteTodo(@PathVariable Long id){
		todoService.deleteTodo(id);
	}
}
