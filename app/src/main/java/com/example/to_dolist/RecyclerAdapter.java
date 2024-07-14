package com.example.to_dolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView task;
        ImageView tickBox, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task=itemView.findViewById(R.id.task);
            tickBox=itemView.findViewById(R.id.tickBox);
            deleteBtn=itemView.findViewById(R.id.delete);
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

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.task.setText(modelList.get(position).g);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
