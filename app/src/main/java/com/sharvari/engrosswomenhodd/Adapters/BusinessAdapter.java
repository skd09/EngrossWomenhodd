package com.sharvari.engrosswomenhodd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProfileActivity.class);
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
        ImageView info, more;
        public MyViewHolder(View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.info);
            more = itemView.findViewById(R.id.ic_more);
        }
    }

    public interface SetMoreClickListener{
        public void OnMoreClick(View v, int position);
    }

    public void OnMoreClickListener(SetMoreClickListener setMoreClickListener){
        this.setMoreClickListener = setMoreClickListener;
    }
}
