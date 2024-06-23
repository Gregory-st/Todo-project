package com.tracer.todo_tracer.repository;

import com.tracer.todo_tracer.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByUser_Id(Long id);
}
