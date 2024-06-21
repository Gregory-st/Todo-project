package com.tracer.todo_tracer.controller;

import ch.qos.logback.core.model.Model;
import com.tracer.todo_tracer.dto.TodoModelDto;
import com.tracer.todo_tracer.response.RedirectResponse;
import com.tracer.todo_tracer.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{login}/createTodo")
    public String createTodo(Model model){
        return "web/createTodo";
    }

    @PostMapping("/{login}/createTodo")
    public ResponseEntity<RedirectResponse> createTodoPost(
            @RequestBody TodoModelDto todo,
            @PathVariable String login,
            Model model
    ){

        RedirectResponse response = new RedirectResponse();
        try {
            todoService.addTodo(todo, login);
            response.setStatusCode(1);
            response.setSuccess(true);
            response.setRedirectPath("/v1/todo/" + login);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            response.setSuccess(false);
            response.setStatusCode(0);
            response.setRedirectPath("/v1/todo/" + login + "/createTodo");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}
