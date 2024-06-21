package com.tracer.todo_tracer.service;

import com.tracer.todo_tracer.entity.TodoEntity;
import com.tracer.todo_tracer.dto.TodoModelDto;
import com.tracer.todo_tracer.entity.UserEntity;
import com.tracer.todo_tracer.exception.ExceptionConvertPriority;
import com.tracer.todo_tracer.priority.TodoPriority;
import com.tracer.todo_tracer.repository.TodoRepository;
import com.tracer.todo_tracer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public void addTodo(TodoModelDto addTodoModel, String login) throws ExceptionConvertPriority {
        TodoEntity todoEntity = new TodoEntity();
        UserEntity userEntity = userRepository
                .findByLogin(login)
                .orElseThrow();

        todoEntity.setTitle(addTodoModel.getTitle());
        todoEntity.setDescription(addTodoModel.getDescription());
        todoEntity.setCreateAt(addTodoModel.getDateExpiration());
        todoEntity.setTodoPriority(TodoPriority
                .convert(addTodoModel
                        .getPriority()
                ));
        todoEntity.setUser(userEntity);
        todoEntity.setStatus(false);


        todoRepository.save(todoEntity);
    }
}
