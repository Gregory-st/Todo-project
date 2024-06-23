package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.dto.StatusTodoDto;
import com.tracer.todo_tracer.response.BaseSuccessResponse;
import com.tracer.todo_tracer.service.AuthenticationService;
import com.tracer.todo_tracer.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class MainController {

    private final TodoService todoService;

    @GetMapping
    public String mainPage(Model model){
        return "index";
    }

    @GetMapping("/{user}")
    public String homePage(@PathVariable String user, Model model){

        model.addAttribute("tasks", todoService.getTodos(user));
        model.addAttribute("url", user + "/createTodo");

        return "index";
    }

    @PutMapping("/{user}")
    public BaseSuccessResponse updateTodo(
            @PathVariable String user,
            @RequestBody StatusTodoDto statusTodoDto,
            Model model
    ){
        todoService.updateStatusTodo(statusTodoDto);

        BaseSuccessResponse response = new BaseSuccessResponse();

        response.setSuccess(true);
        response.setMessage("Success");
        response.setStatusCode(1);

        return response;
    }
}
