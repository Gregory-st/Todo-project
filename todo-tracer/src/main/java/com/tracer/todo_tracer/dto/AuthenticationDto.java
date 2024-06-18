package com.tracer.todo_tracer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    private String email;
    private String password;
}
