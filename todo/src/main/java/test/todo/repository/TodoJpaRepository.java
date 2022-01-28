package test.todo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import test.todo.dto.TodoRequest.Search;
import test.todo.entity.Todo;

@Repository
@RequiredArgsConstructor
public class TodoJpaRepository {

	private final EntityManager em;
	
	public List<Todo> findAll(Search search){
		
		return em.createQuery("select t from Todo t ", Todo.class)
			.setMaxResults(search.getLimit())
			.setFirstResult(search.getSkip())
			.getResultList();
	}
}
