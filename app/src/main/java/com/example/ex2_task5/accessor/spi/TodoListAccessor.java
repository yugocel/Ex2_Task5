package com.example.ex2_task5.accessor.spi;

import com.example.ex2_task5.model.api.Todo;

import java.util.List;

/*
The TodoListAccessor is an interface that abstracts the data access operations for the to-do list.
It's implemented by TodoListAccessorImpl.
*/

public interface TodoListAccessor {
    List<Todo> getTodoList();
    void addTodo(Todo todo);
}
