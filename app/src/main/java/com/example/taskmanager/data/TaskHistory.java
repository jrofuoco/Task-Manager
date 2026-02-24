package com.example.taskmanager.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_histroy")
public class TaskHistory {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String description;
    public String time;
    public String date;
}
