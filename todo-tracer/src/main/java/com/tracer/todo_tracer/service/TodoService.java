package com.tracer.todo_tracer.service;

import com.tracer.todo_tracer.comparator.TodoComparatorPriority;
import com.tracer.todo_tracer.dto.StatusTodoDto;
import com.tracer.todo_tracer.entity.TodoEntity;
import com.tracer.todo_tracer.dto.TodoModelDto;
import com.tracer.todo_tracer.entity.UserEntity;
import com.tracer.todo_tracer.exception.ExceptionConvertPriority;
import com.tracer.todo_tracer.exception.ExceptionNotFoundTodo;
import com.tracer.todo_tracer.model.TodoModel;
import com.tracer.todo_tracer.priority.TodoPriority;
import com.tracer.todo_tracer.repository.TodoRepository;
import com.tracer.todo_tracer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<TodoModel> getTodos(String login){

        UserEntity userEntity = userRepository.findByLogin(login).orElseThrow();
        return todoRepository
                .findByUser_Id(userEntity.getId())
                .stream()
                .map(TodoModel::new)
                .sorted(new TodoComparatorPriority())
                .toList();
    }

    public void updateStatusTodo(StatusTodoDto statusTodoDto){

        TodoEntity todoEntity = todoRepository.findById(statusTodoDto.getId())
                .orElseThrow();

        todoEntity.setStatus(statusTodoDto.getStatus());
        todoRepository.save(todoEntity);
    }

    public void deleteTodoAt(Long id) throws ExceptionNotFoundTodo {

        todoRepository
                .findById(id)
                .orElseThrow(ExceptionNotFoundTodo::new);

        todoRepository.deleteById(id);
    }
}
