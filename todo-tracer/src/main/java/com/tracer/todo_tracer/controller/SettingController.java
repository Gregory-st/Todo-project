package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class SettingController {

    private final AuthenticationService authService;

    @GetMapping("/{login}/person")
    public String setting(
            @PathVariable String login,
            Model model){

        model.addAttribute("user", authService.getUserByLogin(login));
        model.addAttribute("enable", true);

        return "web/setting";
    }
}
