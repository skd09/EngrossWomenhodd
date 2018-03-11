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
import com.sharvari.engrosswomenhodd.Activities.ProfileActivity;
import com.sharvari.engrosswomenhodd.Pojos.Business;
import com.sharvari.engrosswomenhodd.Pojos.News;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.MyViewHolder>{

    private ArrayList<Business> arrayList = new ArrayList<>();
    private Context context;
    private SetMoreClickListener setMoreClickListener;

    public BusinessAdapter(ArrayList<Business> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_business_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Business b = arrayList.get(position);

        holder.company.setText(b.getCompany());
        holder.description.setText(b.getDescription());
        holder.name.setText(b.getName());
        holder.date.setText(b.getDate());

        Glide.with(context).load(b.getUrl()).into(holder.picture);

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProfileActivity.class);
                i.putExtra("UserId",b.getUserId());
                i.putExtra("Page","Business");
                context.startActivity(i);
            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMoreClickListener.OnMoreClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView info, more, picture;
        TextView company, description, name, date;
        public MyViewHolder(View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.info);
            more = itemView.findViewById(R.id.ic_more);
            company = itemView.findViewById(R.id.company);
            description = itemView.findViewById(R.id.description);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            picture = itemView.findViewById(R.id.picture);
        }
    }

    public interface SetMoreClickListener{
        public void OnMoreClick(View v, int position);
    }

    public void OnMoreClickListener(SetMoreClickListener setMoreClickListener){
        this.setMoreClickListener = setMoreClickListener;
    }
}
