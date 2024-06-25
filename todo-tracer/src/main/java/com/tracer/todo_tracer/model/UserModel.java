package com.tracer.todo_tracer.model;

import com.tracer.todo_tracer.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    private String firstname;
    private String lastname;
    private String email;

    public UserModel(UserEntity userEntity) {

        this.firstname = userEntity.getFirstname();
        this.lastname = userEntity.getLastname();
        this.email = userEntity.getEmail();
    }
}
