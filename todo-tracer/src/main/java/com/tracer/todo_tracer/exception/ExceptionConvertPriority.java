package com.tracer.todo_tracer.exception;

public class ExceptionConvertPriority extends Exception{
    public ExceptionConvertPriority(String message) {
        super(message);
    }

    public ExceptionConvertPriority() {
        super("Не удалось конвертировать String в TodoPriority не верный формат строки");
    }
}
