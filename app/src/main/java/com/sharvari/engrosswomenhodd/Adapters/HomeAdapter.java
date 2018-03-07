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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharvari.engrosswomenhodd.Activities.ProfileActivity;
import com.sharvari.engrosswomenhodd.Activities.TaskActivity;
import com.sharvari.engrosswomenhodd.Fragments.HomeFragment;
import com.sharvari.engrosswomenhodd.Pojos.home;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

    private ArrayList<home> homeArrayList = new ArrayList<>();
    private Context context;


    public HomeAdapter(ArrayList<home> homeArrayList, Context context) {
        this.homeArrayList = homeArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_home_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final home h = homeArrayList.get(position);
        Glide.with(context).load(R.drawable.img_bg).apply(RequestOptions.circleCropTransform()).into(holder.picture);
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProfileActivity.class);
                i.putExtra("UserId",h.getId());
                i.putExtra("Location",h.getLocation());
                context.startActivity(i);
            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, TaskActivity.class);
                context.startActivity(i);
            }
        });

        if(h.getPicture().equals("1")){
            holder.picture.setImageDrawable(context.getResources().getDrawable(R.drawable.img_teenager));
        }else if(h.getPicture().equals("2")){
            holder.picture.setImageDrawable(context.getResources().getDrawable(R.drawable.img_women));
        }else if(h.getPicture().equals("3")){
            holder.picture.setImageDrawable(context.getResources().getDrawable(R.drawable.img_women));
        }

        holder.name.setText(h.getName());
        holder.leftDays.setText(h.getLeftDays());
        holder.request.setText(h.getRequest());
        holder.description.setText(h.getDescription());
        holder.amount.setText(h.getAmount());
        holder.location.setText(h.getLocation());
        holder.title.setText(h.getTitle());
    }

    @Override
    public int getItemCount() {
        return homeArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView info, more, picture;
        TextView name, leftDays, request, title, description, amount,location;
        public MyViewHolder(View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.info);
            picture = itemView.findViewById(R.id.picture);
            more = itemView.findViewById(R.id.ic_more);

            name = itemView.findViewById(R.id.name);
            leftDays = itemView.findViewById(R.id.leftDays);
            request = itemView.findViewById(R.id.request);
            description = itemView.findViewById(R.id.description);
            amount = itemView.findViewById(R.id.amount);
            location = itemView.findViewById(R.id.location);
            title = itemView.findViewById(R.id.title);
        }
    }
}
