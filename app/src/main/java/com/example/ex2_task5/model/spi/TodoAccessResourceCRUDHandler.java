package com.example.ex2_task5.model.spi;

import com.example.ex2_task5.model.api.Todo;

import java.util.ArrayList;

/**
 * CRUD interface for handling data items
 *
 * @author Joern Kreutel
 *
 */

public interface TodoAccessResourceCRUDHandler {
    public Todo readDataItem(Long id);

    public ArrayList<Todo> readAllDataItem();

    public void deleteDataItem(Long id);

    public Todo createDataItem(Todo item);

    public Todo updateDataItem(Todo item);
}
