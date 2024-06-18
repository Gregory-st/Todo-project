package com.tracer.todo_tracer.service;

import com.tracer.todo_tracer.entity.TodoEntity;
import com.tracer.todo_tracer.dto.TodoModelDto;
import com.tracer.todo_tracer.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public void addTodo(TodoModelDto addTodoModel){
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setTitle(addTodoModel.getTitle());
        todoEntity.setDescription(addTodoModel.getDescription());
        todoEntity.setCreateAt(addTodoModel.getDateExpiration());
        todoEntity.setTodoPriority(addTodoModel.getPriority());
        todoEntity.setStatus(false);

        todoRepository.save(todoEntity);
    }
}
