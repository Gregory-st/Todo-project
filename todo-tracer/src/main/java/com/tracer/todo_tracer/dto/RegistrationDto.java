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
    private String login;
    private String email;
    private String password;

    public Boolean isNotEmpty(){
        return !firstname.isEmpty() &&
               !lastname.isEmpty() &&
               !email.isEmpty() &&
               !password.isEmpty();
    }
}
