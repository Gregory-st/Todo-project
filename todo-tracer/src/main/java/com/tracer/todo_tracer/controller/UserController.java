package com.tracer.todo_tracer.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/todo/person")
public class UserController {

    @GetMapping("/auth")
    public String authentication(Model model){
        return "web/auth/sigin";
    }

    @GetMapping("/register")
    public String registration(Model model){
        return "web/auth/sigup";
    }
}
