package com.tracer.todo_tracer.dto;

import com.tracer.todo_tracer.priority.TodoPriority;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TodoModelDto {

    private String title;
    private String description;
    private String dateExpiration;
    private TodoPriority priority;
}
