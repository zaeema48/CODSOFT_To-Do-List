package com.example.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private RoomDB db;
    private ToDoListDao taskDao;
    public static  RecyclerAdapter adapter;
    public static List<ToDoListModel>taskList;
    private ToDoListDao toDoListDao;

//    private ExecutorService executorService; //to handler the pool of threads
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView addBtn= findViewById(R.id.addBtn);

        db = RoomDB.getInstance(this);  //getting instance of roomDB for DAO
        taskDao=db.toDoListDao();    //with the help of this instance we get the method
        taskList=taskDao.getAll();   //get getAll from Model

        //Setting adapter to recycler view
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskList= new ArrayList<>();
        adapter= new RecyclerAdapter(this, taskList);
        recyclerView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, AddTask.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });

//        executorService= Executors.newSingleThreadExecutor();  //used in searching
    }
}