package com.example.taskmanager.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.taskmanager.R;
import com.example.taskmanager.data.Task;

public class EditTaskDialogFragment extends DialogFragment {

    private EditText etTitle, etDescription, etTime, etDate;
    private Button btnSave, btnCancel, btnDelete;

    private static final String ARG_TASK_ID = "taskId";
    private static final String ARG_TASK_TITLE = "taskTitle";
    private static final String ARG_TASK_DESCRIPTION = "taskDescription";
    private static final String ARG_TASK_TIME = "taskTime";
    private static final String ARG_TASK_DATE = "taskDate";

    public interface EditTaskDialogListener {
        void onTaskSaved(Task task);
        void onTaskDeleted(int taskId);
    }

    public static EditTaskDialogFragment newInstance(TaskUI task) {
        EditTaskDialogFragment fragment = new EditTaskDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TASK_ID, task.getId());
        args.putString(ARG_TASK_TITLE, task.getTitle());
        args.putString(ARG_TASK_DESCRIPTION, task.getDescription());
        args.putString(ARG_TASK_TIME, task.getTime());
        args.putString(ARG_TASK_DATE, task.getDate());
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_task, null);

        etTitle = view.findViewById(R.id.et_edit_task_title);
        etDescription = view.findViewById(R.id.et_edit_task_description);
        etTime = view.findViewById(R.id.et_edit_task_time);
        etDate = view.findViewById(R.id.et_edit_task_date);
        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnDelete = view.findViewById(R.id.btn_delete);


        if (getArguments() != null) {
            etTitle.setText(getArguments().getString(ARG_TASK_TITLE));
            etDescription.setText(getArguments().getString(ARG_TASK_DESCRIPTION));
            etTime.setText(getArguments().getString(ARG_TASK_TIME));
            etDate.setText(getArguments().getString(ARG_TASK_DATE));
        }

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            String time = etTime.getText().toString();
            String date = etDate.getText().toString();

            Task task = new Task(title, description, time, date);
            task.id = getArguments().getInt(ARG_TASK_ID);

            EditTaskDialogListener listener = (EditTaskDialogListener) getActivity();
            listener.onTaskSaved(task);
            dismiss();
        });

        btnCancel.setOnClickListener(v -> {
            dismiss();
        });

        //TODO: Delete Task
        btnDelete.setOnClickListener(v -> {
            int taskId = getArguments().getInt(ARG_TASK_ID);
            EditTaskDialogListener listener = (EditTaskDialogListener) getActivity();
            listener.onTaskDeleted(taskId);
            dismiss();

        });

        builder.setView(view)
                .setTitle("Edit Task");

        return builder.create();
    }
}
