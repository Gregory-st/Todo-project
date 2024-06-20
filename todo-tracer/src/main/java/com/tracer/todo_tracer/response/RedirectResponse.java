package com.tracer.todo_tracer.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedirectResponse extends BaseSuccessResponse {

    private String redirectPath;
}
