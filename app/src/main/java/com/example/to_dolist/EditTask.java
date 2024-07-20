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

public class EditTask extends AppCompatActivity {

    private RoomDB db;  //private because to avoid naming conflict
    private ToDoListDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_task);
        EditText taskText= findViewById(R.id.addTask);
        ImageView saveBtn= findViewById(R.id.save);
        ImageView delBtn= findViewById(R.id.del);

        db=RoomDB.getInstance(this);
        taskDao=db.toDoListDao();

        //to get the value/object that was passed through intent in Recycler adapter java
        Intent intent = getIntent();
        ToDoListModel toDoListModel = (ToDoListModel) intent.getSerializableExtra("toDoList"); //serialzable is used to pass the whole object (attributes) without it we need to pass all attributes separately and with string

        taskText.setText(toDoListModel.getTask());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adding a new task in the list
                String task= taskText.getText().toString();
                if(!task.isEmpty()) {
                    Toast.makeText(EditTask.this, "Task Edited", Toast.LENGTH_SHORT).show();
                    toDoListModel.setTask(task);
                    taskDao.insert(toDoListModel);
                    taskList.clear();
                    taskList.addAll(taskDao.getAll());
                    adapter.notifyDataSetChanged();
                    finish(); //to go to prev page
                }
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDao.deleteTask(toDoListModel.getId());
                taskList.clear();
                taskList.addAll(taskDao.getAll());
                adapter.notifyDataSetChanged();
                Toast.makeText(EditTask.this, "Task Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}