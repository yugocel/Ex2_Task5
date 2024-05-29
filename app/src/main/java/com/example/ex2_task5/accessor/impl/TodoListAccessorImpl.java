package com.example.ex2_task5.accessor.impl;

import android.content.Context;
import android.content.res.Resources;
import com.example.ex2_task5.R;
import com.example.ex2_task5.accessor.spi.TodoListAccessor;
import com.example.ex2_task5.model.api.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoListAccessorImpl implements TodoListAccessor {
    private final List<Todo> todoList;
    private final Context context;

    public TodoListAccessorImpl(Context context) {
        this.context = context;
        todoList = new ArrayList<>();
        initializeTodoList(context);
    }

    private void initializeTodoList(Context context) {
        Resources resources = context.getResources();
        String[] todoStrings = resources.getStringArray(R.array.list);

        for (String todoString : todoStrings) {
            String[] todoComponents = todoString.split(", ");
            long id = Long.parseLong(todoComponents[0]);
            String title = todoComponents[1].replace("\"", "");
            String description = todoComponents[2].replace("\"", "");
            boolean finished = Boolean.parseBoolean(todoComponents[3]);
            boolean favourite = Boolean.parseBoolean(todoComponents[4]);
            long due_date = Long.parseLong(todoComponents[5]);

            Todo todo = new Todo(id, title, description, finished, favourite, due_date);
            todoList.add(todo);
        }
    }

    @Override
    public List<Todo> getTodoList() {
        return todoList;
    }

    @Override
    public void addTodo(Todo todo) {
        todoList.add(todo);
    }
}