package com.tracer.todo_tracer.comparator;

import com.tracer.todo_tracer.entity.TodoEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;

public class TodoComparatorDate implements Comparator<TodoEntity> {

    @Override
    public int compare(TodoEntity entity1, TodoEntity entity2) {
        LocalDate today = LocalDate.now();
        LocalDate entityDate1 = Date
                .valueOf(entity1.getCreateAt())
                .toLocalDate();
        LocalDate entityDate2 = Date
                .valueOf(entity2.getCreateAt())
                .toLocalDate();

        return Integer.compare(today.compareTo(entityDate1), today.compareTo(entityDate2));
    }
}
