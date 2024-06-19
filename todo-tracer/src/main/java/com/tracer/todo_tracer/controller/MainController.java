package com.tracer.todo_tracer.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/todo")
public class MainController {

    @GetMapping
    public String mainPage(Model model){
        return "index";
    }

    @GetMapping("/{user}")
    public String homePage(@PathVariable String user, Model model){

        return "index";
    }
}
