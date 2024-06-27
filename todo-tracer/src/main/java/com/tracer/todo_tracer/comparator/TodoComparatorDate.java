package com.tracer.todo_tracer.comparator;

import com.tracer.todo_tracer.entity.TodoEntity;
import com.tracer.todo_tracer.model.TodoModel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;

public class TodoComparatorDate implements Comparator<TodoModel> {

    @Override
    public int compare(TodoModel entity1, TodoModel entity2) {
        LocalDate today = LocalDate.now();
        LocalDate entityDate1 = Date
                .valueOf(entity1.getSqlDateCreate())
                .toLocalDate();
        LocalDate entityDate2 = Date
                .valueOf(entity2.getSqlDateCreate())
                .toLocalDate();

        return Integer.compare(today.compareTo(entityDate2), today.compareTo(entityDate1));
    }
}
