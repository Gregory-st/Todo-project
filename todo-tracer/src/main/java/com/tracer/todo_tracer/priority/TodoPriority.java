package com.tracer.todo_tracer.priority;

import com.tracer.todo_tracer.exception.ExceptionConvertPriority;

public enum TodoPriority {
    LOW("Низкий"),
    MEDIUM("Средний"),
    HIGH("Высокий");

    private final String title;
    TodoPriority(String title){ this.title = title; }

    public static TodoPriority convert(String priority) throws ExceptionConvertPriority {
        return switch (priority.toUpperCase()) {
            case "LOW" -> LOW;
            case "MEDIUM" -> MEDIUM;
            case "HIGH" -> HIGH;
            default -> throw new ExceptionConvertPriority();
        };
    }

    @Override
    public String toString() {
        return title;
    }
}
