package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.dto.StatusTodoDto;
import com.tracer.todo_tracer.response.BaseSuccessResponse;
import com.tracer.todo_tracer.response.RedirectResponse;
import com.tracer.todo_tracer.service.AuthenticationService;
import com.tracer.todo_tracer.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class MainController {

    private final TodoService todoService;
    private final AuthenticationService authService;

    @GetMapping
    public String mainPage(Model model){
        return "index";
    }

    @GetMapping("/{login}")
    public String homePage(@PathVariable String login, Model model){

        if(authService.isExpired(login)) return "redirect:/v1/todo/person/auth";

        model.addAttribute("tasks", todoService.getTodos(login));
        model.addAttribute("url", login + "/createTodo");

        return "index";
    }

    @PutMapping("/{login}")
    public ResponseEntity<BaseSuccessResponse> updateTodo(
            @PathVariable String login,
            @RequestBody StatusTodoDto statusTodoDto
    ){

        BaseSuccessResponse response = new BaseSuccessResponse();

        try{
            todoService.updateStatusTodo(statusTodoDto);
            response.setSuccess(true);
            response.setMessage("Success");
            response.setStatusCode(1);
        }
        catch (Exception e){
            response.setSuccess(false);
            response.setStatusCode(0);
            response.setMessage("Bad request");

            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{login}/{id}")
    public ResponseEntity<BaseSuccessResponse> deleteTodo(
            @PathVariable String login,
            @PathVariable Long id
    ){

        BaseSuccessResponse response = new RedirectResponse();

        return ResponseEntity.ok(response);
    }
}
