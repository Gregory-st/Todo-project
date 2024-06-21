package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class MainController {

    private final AuthenticationService service;

    @GetMapping
    public String mainPage(Model model){
        return "index";
    }

    @GetMapping("/{user}")
    public String homePage(@PathVariable String user, Model model){

        model.addAttribute("url", user + "/createTodo");



        return "index";
    }
}
