package com.tracer.todo_tracer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDto {

    private String firstname;
    private String lastname;
    private String login;
    private String email;
    private String password;
}
