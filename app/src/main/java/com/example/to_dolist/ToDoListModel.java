package com.example.to_dolist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "toDoList")
public class ToDoListModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "task")
    private String task="";

    @ColumnInfo(name = "done")
    private boolean done = false;

    public ToDoListModel() {
    }

    public ToDoListModel(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
