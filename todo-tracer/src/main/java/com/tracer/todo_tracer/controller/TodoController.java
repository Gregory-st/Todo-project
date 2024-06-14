package com.tracer.todo_tracer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/todo")
public class TodoController {

    @GetMapping
    public ResponseEntity<String> serverIsWork(){
        return ResponseEntity.ok("Сервер работает");
    }
}
