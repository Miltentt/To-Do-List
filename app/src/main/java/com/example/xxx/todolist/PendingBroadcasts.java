package com.example.xxx.todolist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;
@Entity
public class PendingBroadcasts {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "idd")
    private int idd;
    public PendingBroadcasts (int idd)
    {
  this.idd=idd;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }
}