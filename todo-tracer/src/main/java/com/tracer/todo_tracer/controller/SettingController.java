package com.tracer.todo_tracer.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/todo/setting")
public class SettingController {

    @GetMapping
    public String setting(Model model){
        return "web/setting";
    }
}
