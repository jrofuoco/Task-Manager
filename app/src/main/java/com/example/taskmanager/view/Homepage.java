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

import com.example.taskmanager.R;
import com.example.taskmanager.viewmodel.AddTaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Homepage extends AppCompatActivity {
    private AddTaskViewModel addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        addTask = new ViewModelProvider(this).get(AddTaskViewModel.class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTaskDialog();
            }
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
                    addTask.addTask(title, description, time, date);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}