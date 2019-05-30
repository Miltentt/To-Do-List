package com.example.xxx.todolist;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

@Database(entities = Task.class, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {

   public abstract  TaskDao taskDao();

    @Override
    public void clearAllTables() {

    }
}
