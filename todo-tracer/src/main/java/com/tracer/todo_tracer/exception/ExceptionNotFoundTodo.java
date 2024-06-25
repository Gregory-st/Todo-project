package com.tracer.todo_tracer.exception;

public class ExceptionNotFoundTodo extends Exception{

    public ExceptionNotFoundTodo() {
        super("Не верный номер задания");
    }

    public ExceptionNotFoundTodo(String message) {
        super(message);
    }
}
