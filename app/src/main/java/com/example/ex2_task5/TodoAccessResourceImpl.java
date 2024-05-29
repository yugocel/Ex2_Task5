package com.example.ex2_task5;

import com.example.ex2_task5.model.api.Todo;
import com.example.ex2_task5.model.spi.TodoAccessResourceCRUDHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TodoAccessResourceImpl implements TodoAccessResourceCRUDHandler {
    private Map<Long, Todo> data = new HashMap<>();
    @Override
    public Todo readDataItem(Long id) {
        return data.get(id);
    }

    @Override
    public ArrayList<Todo> readAllDataItem() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void deleteDataItem(Long id) {
        data.remove(id);
    }

    @Override
    public Todo createDataItem(Todo item) {
        data.put(item.getId(), item);
        return item;
    }

    @Override
    public Todo updateDataItem(Todo item) {
        data.put(item.getId(), item);
        return item;
    }

}
