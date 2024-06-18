package com.tracer.todo_tracer.controller;

import ch.qos.logback.core.model.Model;
import com.tracer.todo_tracer.dto.AuthenticationDto;
import com.tracer.todo_tracer.dto.RegistrationDto;
import com.tracer.todo_tracer.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1/todo/person")
public class UserController {

    @Autowired
    private AuthenticationService authService;

    @GetMapping("/auth")
    public String authentication(Model model){
        return "web/auth/sigin";
    }

    @PostMapping("/auth")
    public String authUser(
            @RequestParam String email_user,
            @RequestParam String pass_user,
            Model model
            ){

        String page = authService.authentication(AuthenticationDto
                .builder()
                .email(email_user)
                .password(pass_user)
                .build()
        );

        return "redirect:" + page;
    }

    @GetMapping("/register")
    public String registration(Model model){
        return "web/auth/sigup";
    }

    @PostMapping("/register")
    public String regUser(
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String email_user,
            @RequestParam String pass_user,
            Model model
    ){

        String page = authService.registration(RegistrationDto
                .builder()
                .firstname(first_name)
                .lastname(last_name)
                .email(email_user)
                .password(pass_user)
                .build()
        );

        return "redirect:" + page;
    }
}
