package com.tracer.todo_tracer.controller;

import com.tracer.todo_tracer.dto.StatusTodoDto;
import com.tracer.todo_tracer.exception.ExceptionNotFoundTodo;
import com.tracer.todo_tracer.model.TodoModel;
import com.tracer.todo_tracer.response.BaseSuccessResponse;
import com.tracer.todo_tracer.response.RedirectResponse;
import com.tracer.todo_tracer.service.AuthenticationService;
import com.tracer.todo_tracer.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Map;

@Controller
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class MainController {

    private final TodoService todoService;
    private final AuthenticationService authService;
    private String nameComparator = "priority";
    private final Map<String, Comparator<TodoModel>> comparators;

    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("urlSettings", "/v1/todo/person/auth");
        return "index";
    }

    @GetMapping("/{login}/")
    public String homePageRedirect(@PathVariable String login) {
        return "redirect:/v1/todo/"+login;
    }

    @GetMapping("/{login}")
    public String homePage(@PathVariable String login, Model model){

        try {
            if (authService.isExpired(login)) return "redirect:/v1/todo/person/auth";
        }
        catch (Exception e){
            return "redirect:/v1/todo/person/auth";
        }

        model.addAttribute("tasks", todoService.getTodos(login, comparators.get(nameComparator)));
        model.addAttribute("urlCreate", login + "/createTodo");
        model.addAttribute("urlSettings", login + "/person");
        model.addAttribute("enable", true);

        return "index";
    }

    @PatchMapping("/{login}/{sort}")
    public ResponseEntity<BaseSuccessResponse> changeSorted(@PathVariable String login,
                                                            @PathVariable String sort){
        nameComparator = sort;
        return ResponseEntity.ok(BaseSuccessResponse
                .builder()
                .success(true)
                .statusCode(1)
                .build());
    }

    @PutMapping("/{login}")
    public ResponseEntity<BaseSuccessResponse> updateTodo(
            @PathVariable String login,
            @RequestBody StatusTodoDto statusTodoDto
    ){

        BaseSuccessResponse response = new BaseSuccessResponse();

        try{
            todoService.updateStatusTodo(statusTodoDto);
            response.setSuccess(true);
            response.setMessage("Success");
            response.setStatusCode(1);
        }
        catch (Exception e){
            response.setSuccess(false);
            response.setStatusCode(0);
            response.setMessage("Bad request");

            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{login}/{id}")
    public ResponseEntity<BaseSuccessResponse> deleteTodo(
            @PathVariable String login,
            @PathVariable Long id
    ){

        BaseSuccessResponse response = new RedirectResponse();

        try{
            todoService.deleteTodoAt(id);
            response.setMessage("Задание удалено");
            response.setStatusCode(1);
            response.setSuccess(true);
        }
        catch (ExceptionNotFoundTodo notFound){
            response.setSuccess(false);
            response.setStatusCode(0);
            response.setMessage("Не задание не найдено");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}
