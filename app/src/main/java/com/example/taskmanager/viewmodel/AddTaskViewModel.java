package com.example.taskmanager.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.taskmanager.data.Task;
import com.example.taskmanager.repository.TaskRepository;

public class AddTaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;

    public AddTaskViewModel(Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
    }

    public void addTask(String title, String description, String time, String date) {
        Task newTask = new Task(title, description, time, date);
        taskRepository.insert(newTask);
    }
}
