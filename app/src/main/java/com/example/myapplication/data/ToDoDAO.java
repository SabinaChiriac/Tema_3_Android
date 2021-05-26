package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.liste.models.dbEntities.ToDoItem;

import java.util.List;

@Dao
public interface ToDoDAO {
    @Query("SELECT * FROM toDoItem")
    List<ToDoItem> getAll();

    @Insert
    void insertAll(ToDoItem... toDoItem);

    @Insert
    void insertToDo(ToDoItem toDoItem);

    @Delete
    void delete(ToDoItem toDoItem);

    @Query("DELETE FROM toDoItem WHERE title = :itemTitle")
    void deleteByTitle(String itemTitle);

    @Update
    void update(ToDoItem toDoItem);
}
