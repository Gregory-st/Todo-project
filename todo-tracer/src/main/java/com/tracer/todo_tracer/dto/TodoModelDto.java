package com.tracer.todo_tracer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TodoModelDto {

    private String title;
    private String description;
    private String dateExpiration;
    private String priority;
}
