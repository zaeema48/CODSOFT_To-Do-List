package com.example.to_dolist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ToDoListDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    void insert (String task);

    @Query("SELECT * FROM toDoList ORDER BY id DESC")
    List<String>getAll();

    @Query("UPDATE toDoList SET done =1 WHERE id= :id")
    void taskDone(int id);

    @Query("DELETE FROM toDoList WHERE id = :id")
    void deleteTask(int id);
}
