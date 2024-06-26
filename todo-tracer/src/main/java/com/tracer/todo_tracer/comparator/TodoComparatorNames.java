package com.tracer.todo_tracer.comparator;

import com.tracer.todo_tracer.entity.TodoEntity;

import java.util.Comparator;

public class TodoComparatorNames implements Comparator<TodoEntity> {

    @Override
    public int compare(TodoEntity entity1, TodoEntity entity2) {
        return entity1.getTitle().compareTo(entity2.getTitle());
    }
}
