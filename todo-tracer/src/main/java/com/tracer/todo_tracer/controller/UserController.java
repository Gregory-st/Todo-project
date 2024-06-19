package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.entity.UserEntity;
import org.springframework.ui.Model;
import com.tracer.todo_tracer.dto.AuthenticationDto;
import com.tracer.todo_tracer.dto.RegistrationDto;
import com.tracer.todo_tracer.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            @ModelAttribute AuthenticationDto user,
            Model model
    ){

        model.addAttribute("user", user);
        UserEntity userEntity = authService.authentication(user);

        return "redirect:/v1/todo/" + userEntity.getLogin();
    }

    @GetMapping("/register")
    public String registration(Model model){
        return "web/auth/sigup";
    }

    @PostMapping("/register")
    public String regUser(
            @ModelAttribute RegistrationDto user,
            Model model
    ){
        model.addAttribute("user", user);
        if(!user.isNotEmpty()) return "redirect:/v1/todo/person/register?err";

        String page = authService.registration(user);

        return "redirect:" + page;
    }
}
