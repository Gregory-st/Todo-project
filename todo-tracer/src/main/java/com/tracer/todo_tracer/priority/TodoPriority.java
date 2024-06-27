package com.tracer.todo_tracer.priority;

import com.tracer.todo_tracer.exception.ExceptionConvertPriority;
import lombok.Getter;

@Getter
public enum TodoPriority {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private final int impotent;
    TodoPriority(int priority){ this.impotent = priority; }

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
        String[] names = new String[]{
                "Низкий",
                "Средний",
                "Высокий"
        };
        return names[impotent - 1];
    }
}
