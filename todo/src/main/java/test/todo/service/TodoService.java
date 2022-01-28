package test.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import test.todo.dto.TodoListResponse;
import test.todo.dto.TodoRequest.CreateRequest;
import test.todo.dto.TodoRequest.Search;
import test.todo.dto.TodoRequest.UpdateRequest;
import test.todo.dto.TodoResponse;
import test.todo.entity.Todo;
import test.todo.repository.TodoJpaRepository;
import test.todo.repository.TodoRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

	private final TodoRepository todoRepository;
	private final TodoJpaRepository todoJpaRepository;
	
	public List<TodoListResponse> todoAll(Search search) {
		return todoJpaRepository.findAll(search).stream().map(t -> new TodoListResponse(t)).collect(Collectors.toList());
	}

	public TodoResponse todoGetOne(Long id) {
		return new TodoResponse(todoRepository.findById(id).orElseThrow());
	}

	@Transactional
	public TodoResponse saveTodo(CreateRequest todoRequest) {
		return new TodoResponse(todoRepository.save(Todo.createTodo(todoRequest.getName())));
	}

	@Transactional
	public TodoResponse updateTodo(Long id, UpdateRequest todoRequest) {
		Todo getTodo = todoRepository.findById(id).orElseThrow();
		getTodo.update(todoRequest);
		return new TodoResponse(getTodo);
	}

	@Transactional
	public void deleteTodo(Long id) {
		todoRepository.delete(todoRepository.findById(id).orElseThrow());
	}

}
