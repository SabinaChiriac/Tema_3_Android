package com.example.myapplication;

import android.app.Application;
import androidx.room.Room;
import com.example.myapplication.data.ToDoDataBase;

public class ApplicationController extends  Application{

    private  static ApplicationController instance;
    private static  ToDoDataBase toDoDataBase;
    private  final String toDoDataBaseName="toDoDB";

    @Override
    public void onCreate(){
        super.onCreate();
        instance=this;
        setupDataBase();
    }
    private void setupDataBase(){
        toDoDataBase=Room.databaseBuilder(
                getApplicationContext(),
                ToDoDataBase.class,
                toDoDataBaseName).addMigrations(ToDoDataBase.MIGRATION_2_3).build();
    }
    public static ToDoDataBase getToDoDataBase(){return toDoDataBase;}
    public static ApplicationController getInstance(){return instance;}
}
