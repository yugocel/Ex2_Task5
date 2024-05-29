package com.example.ex2_task5.model.api;

import java.io.Serializable;

public class Todo implements Serializable {

    private long id;
    private String title;
    private String description;
    private boolean isFinished;
    private boolean isImportant;
    private long dueDate;

    public Todo(long id, String title, String description, boolean isFinished, boolean isImportant, long dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isFinished = isFinished;
        this.isImportant = isImportant;
        this.dueDate = dueDate;
    }
    // Default constructor
    public Todo() {
        this.id = -1;
        this.title = "default";
        this.description = "default description";
        this.isFinished = false;
        this.isImportant = false;
        this.dueDate = -1;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }
}
