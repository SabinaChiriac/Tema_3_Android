package com.example.myapplication.data.tasks;

import android.os.AsyncTask;

import com.example.myapplication.data.ToDoDataBase;
import com.example.myapplication.data.ToDoRepository;
import com.example.myapplication.models.dbEntities.ToDoItem;

public class DeleteByTitleToDoTask extends AsyncTask<String,Void,Void> {
    private ToDoDataBase toDoDataBase;
    private ToDoRepository.OnSuccesListener listener;

    public DeleteByTitleToDoTask(ToDoDataBase toDoDataBase, ToDoRepository.OnSuccesListener listener) {
        this.toDoDataBase = toDoDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(String... titles) {
        toDoDataBase.toDoDAO().deleteByTitle(titles[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}