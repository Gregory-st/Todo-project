package com.tracer.todo_tracer.model;

import com.tracer.todo_tracer.entity.TodoEntity;
import com.tracer.todo_tracer.priority.TodoPriority;
import com.tracer.todo_tracer.priority.TodoState;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class TodoModel {

    private Long id;
    private String title;
    private String description;
    private String completed;
    private String createAt;
    private TodoPriority priority;
    private TodoState state;

    public TodoModel(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.description = todoEntity.getDescription();

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

        if(todoEntity.getStatus()){
            this.state = TodoState.COMPLETE;
        }
        else{
            LocalDate expired = Date
                    .valueOf(todoEntity.getCreateAt())
                    .toLocalDate();
            LocalDate today = LocalDate.now();

            if(expired.isBefore(today)){
                this.state = TodoState.FAILED;
            }
            else {
                this.state = TodoState.WAITED;
            }
        }
    }

    public String getState() {
        return state.getTitle();
    }

    public Boolean isComplete(){
        return state != TodoState.WAITED;
    }
}
