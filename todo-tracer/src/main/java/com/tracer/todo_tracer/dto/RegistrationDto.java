package com.tracer.todo_tracer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
