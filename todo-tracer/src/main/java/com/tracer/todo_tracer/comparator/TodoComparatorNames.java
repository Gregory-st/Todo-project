package com.tracer.todo_tracer.comparator;

import com.tracer.todo_tracer.model.TodoModel;

import java.util.Comparator;

public class TodoComparatorNames implements Comparator<TodoModel> {

    @Override
    public int compare(TodoModel entity1, TodoModel entity2) {
        return entity1.getTitle().compareTo(entity2.getTitle());
    }
}
