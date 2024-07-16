package com.example.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public static  RecyclerAdapter adapter;
    public static List<ToDoListModel>taskList;
    private ToDoListDao toDoListDao;

    private ExecutorService executorService; //to handler the pool of threads

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView addBtn= findViewById(R.id.addBtn);

        //Setting adapter to recycler view
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskList= new ArrayList<>();
        adapter= new RecyclerAdapter(this, taskList);
        recyclerView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, TaskPage.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });

        executorService= Executors.newSingleThreadExecutor(); 
    }
}