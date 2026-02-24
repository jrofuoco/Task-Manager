package com.example.taskmanager.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.viewmodel.AddTaskViewModel;
import com.example.taskmanager.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {
    private AddTaskViewModel addTaskViewModel;
    private TaskViewModel taskViewModel;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        addTaskViewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);

        taskViewModel.getAllTasks().observe(this, tasks -> {
            List<TaskUI> uiTasks = TaskUI.fromTasks(tasks);
            taskAdapter.submitList(uiTasks);
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> {
            showAddTaskDialog();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);
        final EditText etTaskTitle = dialogView.findViewById(R.id.et_task_title);
        final EditText etTaskDescription = dialogView.findViewById(R.id.et_task_description);
        final EditText etTaskTime = dialogView.findViewById(R.id.et_task_time);
        final EditText etTaskDate = dialogView.findViewById(R.id.et_task_date);
        builder.setView(dialogView)
                .setTitle("Add Task")
                .setPositiveButton("Add", (dialog, which) -> {
                    String title = etTaskTitle.getText().toString();
                    String description = etTaskDescription.getText().toString();
                    String time = etTaskTime.getText().toString();
                    String date = etTaskDate.getText().toString();
                    addTaskViewModel.addTask(title, description, time, date);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
