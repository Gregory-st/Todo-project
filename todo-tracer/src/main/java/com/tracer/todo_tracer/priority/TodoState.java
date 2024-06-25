package com.tracer.todo_tracer.priority;

import lombok.Getter;

@Getter
public enum TodoState {
    COMPLETE ("complitetodo"),
    FAILED ("failtodo"),
    WAITED ("waittodo");

    private final String title;
    TodoState(String title) {
        this.title = title;
    }
}
