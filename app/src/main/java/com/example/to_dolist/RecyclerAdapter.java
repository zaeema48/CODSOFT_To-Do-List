package com.example.to_dolist;

import static com.example.to_dolist.MainActivity.adapter;
import static com.example.to_dolist.MainActivity.taskList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView task;
        ImageView tickBox, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task=itemView.findViewById(R.id.task);
            tickBox=itemView.findViewById(R.id.tickBox);
            deleteBtn=itemView.findViewById(R.id.delete);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }

    Context context;
    List<ToDoListModel>modelList= new ArrayList<>();

    public RecyclerAdapter(Context context, List<ToDoListModel> modelList){
        this.context=context;
        this.modelList=modelList;
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.task_layout, null, false);
        return new ViewHolder(view);
    }

    private  RoomDB roomDB;
    private ToDoListDao taskDao;
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        roomDB= RoomDB.getInstance(context);
        taskDao=roomDB.toDoListDao();
        holder.task.setText(modelList.get(position).getTask());

        if(modelList.get(position).isDone()){
            holder.tickBox.setImageResource(R.drawable.check_box_24);
        }

        holder.tickBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modelList.get(position).isDone()){
                   modelList.get(position).setDone(false);
                   taskDao.insert(modelList.get(position));
                    holder.tickBox.setImageResource(R.drawable.empty_box_outline_blank_24  );
                }
                else{
                    modelList.get(position).setDone(true);
                    taskDao.insert(modelList.get(position));
                    holder.tickBox.setImageResource(R.drawable.check_box_24);
                }
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTask.class);
                intent.putExtra("toDoList", modelList.get(position));
                context.startActivity(intent);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDao.deleteTask(modelList.get(position).getId());
                taskList.clear();
                taskList.addAll(taskDao.getAll());
                adapter.notifyDataSetChanged();
                Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}
