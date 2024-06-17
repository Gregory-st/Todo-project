package com.tracer.todo_tracer.entity;

import com.tracer.todo_tracer.priority.TodoPriority;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    private Long id;
    private String title;
    private String description;
    private String createAt;
    private Boolean status;

    @Enumerated(EnumType.STRING)
    private TodoPriority todoPriority;
}
