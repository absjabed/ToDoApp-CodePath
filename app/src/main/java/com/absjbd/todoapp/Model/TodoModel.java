package com.absjbd.todoapp.Model;

import android.support.annotation.Nullable;

/**
 * Created by absjabed on 08/19/17.
 */

public class TodoModel {

    private int id;
    private String TodoName;
    private String DueDate;
    private String Status;
    private String priority;

    public TodoModel(){
    }

    public TodoModel(String todoName, String dueDate, String status, String priority) {
        TodoName = todoName;
        DueDate = dueDate;
        Status = status;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTodoName(String name) {
        TodoName = name;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTodoName() {
        return TodoName;
    }
}
