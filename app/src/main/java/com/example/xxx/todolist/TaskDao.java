package com.example.xxx.todolist;

import java.util.LinkedList;
import java.util.List;
import android.arch.persistence.room.*;


@Dao
public interface TaskDao {
 @Insert
  void Insert(Task... task);
 @Query("SELECT * FROM Task")
  List<Task> loadAllTasks();
 @Delete
 public void delete(Task... task);
 @Update
 public void update(Task... task);


}
