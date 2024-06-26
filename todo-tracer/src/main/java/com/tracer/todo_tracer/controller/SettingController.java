package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.dto.PasswordDto;
import com.tracer.todo_tracer.dto.RegistrationDto;
import com.tracer.todo_tracer.entity.UserEntity;
import com.tracer.todo_tracer.response.BaseSuccessResponse;
import com.tracer.todo_tracer.response.RedirectResponse;
import com.tracer.todo_tracer.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class SettingController {

    private final AuthenticationService authService;
    private Boolean nonEnable = true;

    @GetMapping("/{login}/person")
    public String setting(
            @PathVariable String login,
            Model model){

        model.addAttribute("user", authService.getUserByLogin(login));
        model.addAttribute("enable", nonEnable);

        return "web/setting";
    }

    @GetMapping("/{login}/person/")
    public String switchEnabled(@PathVariable String login){

        nonEnable = !nonEnable;
        return "redirect:/v1/todo/" + login + "/person";
    }

    @PatchMapping("/{login}/person/")
    public ResponseEntity<BaseSuccessResponse> equalPassword(@PathVariable String login,
                                                             @RequestBody PasswordDto passwordDto){

        boolean isEqual = authService.isEquals(login, passwordDto);
        BaseSuccessResponse response = new RedirectResponse();

        if(isEqual) {
            response.setSuccess(true);
            response.setStatusCode(1);
            response.setMessage("Equal");
        }else {
            response.setSuccess(false);
            response.setStatusCode(0);
            response.setMessage("NotEqual");
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{login}/person")
    public ResponseEntity<RedirectResponse> updateUser(@PathVariable String login,
                                                       @RequestBody RegistrationDto updateUser){

        RedirectResponse response = new RedirectResponse();

        UserEntity user = authService.updateUserData(login, updateUser);

        String redirect = "/v1/todo/" + updateUser.getLogin() + "/person";

        response.setRedirectPath(redirect);
        response.setSuccess(true);
        response.setToken(user.getToken());
        response.setStatusCode(1);
        response.setMessage("Update");
        nonEnable = true;

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{login}/person/exit")
    public ResponseEntity<RedirectResponse> logoutUser(@PathVariable String login){

        RedirectResponse response = new RedirectResponse();

        authService.logout(login);

        response.setRedirectPath("/v1/todo");
        response.setSuccess(true);
        response.setToken("");
        response.setStatusCode(1);

        return ResponseEntity.ok(response);
    }
}
