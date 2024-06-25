package com.tracer.todo_tracer.comparator;

import com.tracer.todo_tracer.model.TodoModel;

import java.util.Comparator;

public class TodoComparator implements Comparator<TodoModel> {

    @Override
    public int compare(TodoModel todo1, TodoModel todo2) {
        int impotentTodo1 = todo1
                .getPriority()
                .getImpotent();

        int impotentTodo2 = todo2
                .getPriority()
                .getImpotent();

        return Integer.compare(impotentTodo2, impotentTodo1);
    }
}
