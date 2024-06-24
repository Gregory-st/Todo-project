package com.tracer.todo_tracer.model;

import com.tracer.todo_tracer.entity.TodoEntity;
import com.tracer.todo_tracer.priority.TodoPriority;
import lombok.Getter;
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
    private Boolean state;

    public TodoModel(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.description = todoEntity.getDescription();

        this.state = todoEntity.getStatus();

        if(todoEntity.getStatus())
            completed = "Завершенно";
        else
            completed = "Выполняется";

        this.priority = todoEntity.getTodoPriority();
        String[] subLine = todoEntity
                .getCreateAt()
                .split("-");

        this.createAt = subLine[2] +
                "." +
                subLine[1] +
                "." +
                subLine[0];
    }
}
