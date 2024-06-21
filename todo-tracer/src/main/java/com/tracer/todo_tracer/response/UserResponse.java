package com.tracer.todo_tracer.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse extends BaseSuccessResponse {
    private String login;
}
