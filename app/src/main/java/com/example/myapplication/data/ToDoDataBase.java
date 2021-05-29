package com.example.myapplication.data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myapplication.models.dbEntities.ToDoItem;

import org.jetbrains.annotations.NotNull;

@Databese(entities={ToDoItem.class},version=3)
public abstract class ToDoDataBase extends RoomDataBase {

    public abstract ToDoDAO toDoDAO();

    @NonNull
    @Override
    protected  SupportSQLLiteOpenHelper createOpenHelper(DatabaseConfiguration config){
        return null;
    }
    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE ToDoItem ADD COLUMN description TEXT");
        }
    };
}
