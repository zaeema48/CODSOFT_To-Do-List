package com.example.to_dolist;

import static com.example.to_dolist.MainActivity.adapter;
import static com.example.to_dolist.MainActivity.taskList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTask extends AppCompatActivity {
    private RoomDB db;
    private ToDoListDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText addTask;
        ImageView saveBtn;

        addTask=findViewById(R.id.addTask);
        saveBtn=findViewById(R.id.save);

        db=RoomDB.getInstance(this);
        taskDao=db.toDoListDao();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = addTask.getText().toString();
                if(!task.isEmpty()){
                    Toast.makeText(AddTask.this, "Task Added", Toast.LENGTH_SHORT).show();
//                    model.setTask(task);
                    ToDoListModel model = new ToDoListModel(task);
                    taskDao.insert(model);
                    taskList.clear();
                    taskList.addAll(taskDao.getAll());
                    adapter.notifyDataSetChanged();
                    finish();
                }
            }
        });
    }
}