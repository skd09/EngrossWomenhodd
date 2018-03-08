package com.sharvari.engrosswomenhodd.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharvari.engrosswomenhodd.Pojos.Request;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sharvaridivekar on 08/03/18.
 */

public class MyTaskRequestAdapter extends RecyclerView.Adapter<MyTaskRequestAdapter.MyViewHolder>{

    private ArrayList<Request> arrayList = new ArrayList<>();
    private Context context;

    public MyTaskRequestAdapter(ArrayList<Request> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_request, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Request request = arrayList.get(position);

        holder.name.setText(request.getName());
        String s = request.getStatus().equals("0") ? "PENDING" : "ACCEPTED";
        holder.status.setText(s);
        if(s.equals("ACCEPTED"))
            holder.status.setTextColor(context.getResources().getColor(R.color.colorGreen));
        Date d = new Date(request.getDate());
        String date = new java.text.SimpleDateFormat("dd/MM/yyyy").format(d);
        holder.date.setText(date);
        holder.amount.setText(request.getPrice());
        holder.description.setText(request.getDescription());
        holder.description.setVisibility(View.VISIBLE);

        if(request.getPicture().equals("1")){
            holder.picture.setImageDrawable(context.getResources().getDrawable(R.drawable.img_teenager));
        }else if(request.getPicture().equals("2")){
            holder.picture.setImageDrawable(context.getResources().getDrawable(R.drawable.img_women));
        }else if(request.getPicture().equals("3")){
            holder.picture.setImageDrawable(context.getResources().getDrawable(R.drawable.img_women));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,name, status, amount, description;
        ImageView picture;
        public MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            amount = itemView.findViewById(R.id.amount);
            description = itemView.findViewById(R.id.description);
            picture = itemView.findViewById(R.id.picture);
        }
    }
}