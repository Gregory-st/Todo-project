package com.tracer.todo_tracer.exception;

public class ExistUserWithByLoginException extends Exception{
    public ExistUserWithByLoginException() {
        super("Пользователь с таким логином уже существет");
    }

    public ExistUserWithByLoginException(String message) {
        super(message);
    }
}
