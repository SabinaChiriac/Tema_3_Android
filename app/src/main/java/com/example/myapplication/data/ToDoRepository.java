package com.example.myapplication.data;
import com.example.liste.ApplicationController;
import com.example.liste.data.tasks.DeleteByTitleToDoTask;
import com.example.liste.data.tasks.DeleteToDoTask;
import com.example.liste.data.tasks.GetAllToDosTask;
import com.example.liste.data.tasks.InsertToDoTask;
import com.example.liste.data.tasks.UpdateToDoTask;
import com.example.liste.models.dbEntities.ToDoItem;

import java.util.List;

public class ToDoRepository {
    public static interface OnSuccesListener {
        void onSuccess();
    }

    public static interface OnGetToDosListerner {
        void onSuccess(List<ToDoItem> items);
    }

    private ToDoDataBase toDoDataBase;

    public ToDoRepository() {
        toDoDataBase = ApplicationController.getToDoDataBase();
    }

    public void insertToDo(ToDoItem toDoItem, OnSuccesListener listener) {
        new InsertToDoTask(toDoDataBase, listener).execute(toDoItem);
    }

    public void getAllToDo(OnGetToDosListerner listener) {
        new GetAllToDosTask(toDoDataBase, listener).execute();
    }

    public void updateToDo(ToDoItem toDoItem, OnSuccesListener listener) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new UpdateToDoTask(toDoDataBase, listener).execute(toDoItem);
//            }
//        }, 5000);
        new UpdateToDoTask(toDoDataBase, listener).execute(toDoItem);
    }

    public void deleteToDo(ToDoItem toDoItem, OnSuccesListener listener) {
        new DeleteToDoTask(toDoDataBase, listener).execute(toDoItem);
    }

    public void deleteByTitleToDo(String toDoItem, OnSuccesListener listener) {
        new DeleteByTitleToDoTask(toDoDataBase, listener).execute(toDoItem);
    }
}
