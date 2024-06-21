package com.tracer.todo_tracer.entity;

import com.tracer.todo_tracer.priority.TodoPriority;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Long id;
    private String title;
    private String description;
    private String createAt;
    private Boolean status;

    @Enumerated(EnumType.STRING)
    private TodoPriority todoPriority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
