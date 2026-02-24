package com.example.taskmanager.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.taskmanager.data.AppDatabase;
import com.example.taskmanager.data.Task;
import com.example.taskmanager.data.TaskDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public void insert(Task task) {
        executor.execute(() -> taskDao.insert(task));
    }

    public void update(Task task) {
        executor.execute(() -> taskDao.update(task));
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
}