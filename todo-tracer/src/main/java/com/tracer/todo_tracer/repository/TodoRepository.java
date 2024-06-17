package com.tracer.todo_tracer.repository;

import com.tracer.todo_tracer.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
