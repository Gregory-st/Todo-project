package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.entity.TodoEntity;
import com.tracer.todo_tracer.entity.UserEntity;
import com.tracer.todo_tracer.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

        UserEntity userEntity = null;

        try{
            userEntity = service.getUserByLogin(user);
        }
        catch (Exception e){
            return "redirect:/v1/todo";
        }

        return "index";
    }
}
