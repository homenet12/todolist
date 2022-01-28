package test.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import test.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

	Page<Todo> findAll(Pageable pageable);
}
