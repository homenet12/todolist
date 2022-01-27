package test.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import test.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
