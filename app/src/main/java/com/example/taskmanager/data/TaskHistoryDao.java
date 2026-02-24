package com.example.taskmanager.data;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TaskHistoryDao {
    @Insert
    void insert(TaskHistory taskHistory);
}
