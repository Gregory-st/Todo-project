package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.entity.UserEntity;
import com.tracer.todo_tracer.exception.ExistUserWithByLoginException;
import com.tracer.todo_tracer.response.RedirectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.tracer.todo_tracer.dto.AuthenticationDto;
import com.tracer.todo_tracer.dto.RegistrationDto;
import com.tracer.todo_tracer.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    public ResponseEntity<RedirectResponse> authUser(@RequestBody AuthenticationDto authed){

        RedirectResponse response = new RedirectResponse();
        try{
            UserEntity userEntity = authService.authentication(authed);
            response.setSuccess(true);
            response.setStatusCode(1);
            response.setRedirectPath("/v1/todo/" + userEntity.getLogin());
            response.setToken(userEntity.getToken());
        }
        catch (Exception e){
            response.setSuccess(false);
            response.setStatusCode(0);
            response.setRedirectPath("/v1/todo/person/auth");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/register")
    public String registration(Model model){
        return "web/auth/sigup";
    }

    @PostMapping("/register")
    public ResponseEntity<RedirectResponse> regUser(@RequestBody RegistrationDto user){

        RedirectResponse response = new RedirectResponse();

        response.setStatusCode(1);
        response.setSuccess(true);
        response.setRedirectPath("/v1/todo/person/auth");

        try{
            authService.registration(user);
        }
        catch (ExistUserWithByLoginException exception){
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
            response.setStatusCode(0);

            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}