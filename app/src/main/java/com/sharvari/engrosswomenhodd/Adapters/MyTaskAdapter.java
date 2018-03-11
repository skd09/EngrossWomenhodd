package com.sharvari.engrosswomenhodd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharvari.engrosswomenhodd.Activities.MyTaskRequestActivity;
import com.sharvari.engrosswomenhodd.Activities.TaskActivity;
import com.sharvari.engrosswomenhodd.Activities.UpdateMyTaskActivity;
import com.sharvari.engrosswomenhodd.Pojos.MyTask;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.MyViewHolder>{

    private ArrayList<MyTask> arrayList = new ArrayList<>();
    private Context context;

    public MyTaskAdapter(ArrayList<MyTask> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_my_task_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        MyTask task = arrayList.get(position);
        holder.name.setText(task.getName());
        holder.date.setText(task.getDate()+"   "+task.getRequestCount());
        holder.description.setText(task.getDescription());
        holder.status.setText(task.getStatus());
        holder.location.setText(task.getLocation());
        holder.amount.setText(task.getAmount());

        if(task.getStatus().equals("COMPLETED")){
            holder.status.setTextColor(context.getResources().getColor(R.color.colorGreen));
        }

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask myTask = arrayList.get(position);
                Intent i = new Intent(context, MyTaskRequestActivity.class);
                i.putExtra("TaskId",myTask.getId());
                context.startActivity(i);
            }
        });
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask myTask = arrayList.get(position);
                Intent i = new Intent(context, UpdateMyTaskActivity.class);
                i.putExtra("Task", myTask);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,date,description, status, location,amount;
        ImageView more, info;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            location = itemView.findViewById(R.id.location);
            amount = itemView.findViewById(R.id.amount);
            more = itemView.findViewById(R.id.ic_more);
            info = itemView.findViewById(R.id.info);
        }
    }
}
