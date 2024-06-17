package com.tracer.todo_tracer.controller;

import ch.qos.logback.core.model.Model;
import com.tracer.todo_tracer.exception.ExceptionConvertPriority;
import com.tracer.todo_tracer.dto.TodoModelDto;
import com.tracer.todo_tracer.priority.TodoPriority;
import com.tracer.todo_tracer.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/createTodo")
    public String createTodo(Model model){
        return "web/createTodo";
    }

    @PostMapping("/createTodo")
    public String createTodoPost(
            @RequestParam String task_title,
            @RequestParam String task_description,
            @RequestParam String task_due_date,
            @RequestParam String task_priority,
            Model model
    ){

        try {
            TodoModelDto addTodoModel = TodoModelDto
                    .builder()
                    .title(task_title)
                    .description(task_description)
                    .dateExpiration(task_due_date)
                    .priority(TodoPriority.convert(task_priority))
                    .build();

            todoService.addTodo(addTodoModel);
        }
        catch (ExceptionConvertPriority e){
            return "/createTodo";
        }

        return "redirect:/v1/todo";
    }
}
