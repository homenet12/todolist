package test.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import test.todo.dto.TodoRequest.createRequest;
import test.todo.dto.TodoRequest.updateRequest;
import test.todo.dto.TodoResponse;
import test.todo.entity.Todo;
import test.todo.repository.TodoRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

	private final TodoRepository todoRepository;
	
	public List<TodoResponse> todoAll() {
		return todoRepository.findAll().stream().map(t -> new TodoResponse(t)).collect(Collectors.toList());
	}

	public TodoResponse todoGetOne(Long id) {
		return new TodoResponse(todoRepository.findById(id).orElseThrow());
	}

	@Transactional
	public TodoResponse saveTodo(createRequest todoRequest) {
		return new TodoResponse(todoRepository.save(Todo.createTodo(todoRequest.getName())));
	}

	@Transactional
	public TodoResponse updateTodo(updateRequest todoRequest) {
		Todo getTodo = todoRepository.findById(todoRequest.getId()).orElseThrow();
		getTodo.update(todoRequest);
		return new TodoResponse(getTodo);
	}

	@Transactional
	public void deleteTodo(Long id) {
		todoRepository.delete(todoRepository.findById(id).orElseThrow());
	}

}
