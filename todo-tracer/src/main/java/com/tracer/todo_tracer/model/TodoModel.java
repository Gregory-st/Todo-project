package com.tracer.todo_tracer.model;

import com.tracer.todo_tracer.entity.TodoEntity;
import com.tracer.todo_tracer.priority.TodoPriority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TodoModel {

    private Long id;
    private String title;
    private String description;
    private String completed;
    private String createAt;
    private TodoPriority priority;

    public TodoModel(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.description = todoEntity.getDescription();

        if(todoEntity.getStatus())
            completed = "Завершенно";
        else
            completed = "Выполняется";

        this.priority = todoEntity.getTodoPriority();
        this.createAt = todoEntity.getCreateAt().replace('-', '.');
    }
}
