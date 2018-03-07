package com.sharvari.engrosswomenhodd.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharvari.engrosswomenhodd.Pojos.MyTask;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class MyTaskAdapter  extends RecyclerView.Adapter<MyTaskAdapter.MyViewHolder>{

    private ArrayList<MyTask> arrayList = new ArrayList<>();

    public MyTaskAdapter(ArrayList<MyTask> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_my_task_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyTask task = arrayList.get(position);
        holder.name.setText(task.getName());
        holder.date.setText(task.getDate());
        holder.description.setText(task.getDescription());
        holder.status.setText(task.getStatus());
        holder.location.setText(task.getLocation());
        holder.amount.setText(task.getAmount());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,date,description, status, location,amount;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            location = itemView.findViewById(R.id.location);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
