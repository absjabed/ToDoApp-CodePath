package com.absjbd.todoapp;

/**
 * Created by absjabed on 08/19/17.
 */

public class DataTemp {

    private int id;
    private String TodoName;


    public DataTemp(String n){
        TodoName = n;
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

    public String getName() {
        return TodoName;
    }
}
