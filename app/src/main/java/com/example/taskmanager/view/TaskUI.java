package com.example.taskmanager.view;

import com.example.taskmanager.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskUI {
    private final int id;
    private final String title;
    private final String description;
    private final String time;
    private final String date;

    public TaskUI(int id, String title, String description, String time, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
    public String getDate() { return date; }

    public static List<TaskUI> fromTasks(List<Task> tasks) {
        List<TaskUI> uiTasks = new ArrayList<>();
        for (Task task : tasks) {
            uiTasks.add(new TaskUI(task.getId(), task.getTitle(), task.getDescription(), task.getTime(), task.getDate()));
        }
        return uiTasks;
    }
}
