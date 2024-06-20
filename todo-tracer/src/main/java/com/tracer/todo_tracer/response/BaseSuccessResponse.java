package com.tracer.todo_tracer.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseSuccessResponse {
    private int statusCode;
    private Boolean success;
    private String message;
}
