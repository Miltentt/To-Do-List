package com.example.xxx.todolist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PendingDao {

    @Insert
    void Insert(PendingBroadcasts... pendingBroadcasts);
    @Query("SELECT * FROM PendingBroadcasts")
    List<PendingBroadcasts> loadAllTasks();
    @Delete
    public void delete(PendingBroadcasts... pendingBroadcasts);
    @Update
    public void update(PendingBroadcasts... pendingBroadcasts);
}
