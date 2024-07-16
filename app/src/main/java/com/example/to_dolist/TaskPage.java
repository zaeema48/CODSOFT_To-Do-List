package com.example.to_dolist;

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

import java.util.List;

public class TaskPage extends AppCompatActivity {

    RecyclerAdapter adapter ;
    ToDoListDao toDoListDao;
    List<String> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText addTask= findViewById(R.id.addTask);
        ImageView saveBtn= findViewById(R.id.save);
        ImageView delBtn= findViewById(R.id.del);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adding a new task in the list
                String task= addTask.getText().toString();
                if(!task.isEmpty()) {
                    Toast.makeText(TaskPage.this, "Task Added", Toast.LENGTH_SHORT).show();
                    ToDoListModel model= new ToDoListModel(task);
                    model.setTask(task);
                    toDoListDao.insert(model);
                    taskList.clear();
                    taskList.add(toDoListDao.));
                    adapter.notifyDataSetChanged();
                    finish();
                }
            }
        });
    }
}