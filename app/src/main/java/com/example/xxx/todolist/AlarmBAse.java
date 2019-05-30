package com.example.xxx.todolist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database( entities = PendingBroadcasts.class, version = 3, exportSchema = false)
public abstract class AlarmBAse extends RoomDatabase {
    public abstract  PendingDao PendingDao();

    @Override
    public void clearAllTables() {

    }
}
