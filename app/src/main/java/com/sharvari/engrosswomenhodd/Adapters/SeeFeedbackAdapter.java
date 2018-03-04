package com.sharvari.engrosswomenhodd.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sharvari.engrosswomenhodd.Pojos.SeeFeedback;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class SeeFeedbackAdapter  extends RecyclerView.Adapter<SeeFeedbackAdapter.MyViewHolder>{

    private ArrayList<SeeFeedback> arrayList = new ArrayList<>();
    private Context context;

    public SeeFeedbackAdapter(ArrayList<SeeFeedback> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_feedback_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SeeFeedback news = arrayList.get(position);
        if(news.getImage().equals("1")){
            Glide.with(context).load(context.getResources().getDrawable(R.drawable.img_teenager)).into(holder.picture);
        }else if(news.getImage().equals("2")){
            Glide.with(context).load(context.getResources().getDrawable(R.drawable.img_women)).into(holder.picture);
        }else if(news.getImage().equals("3")){
            Glide.with(context).load(context.getResources().getDrawable(R.drawable.img_old)).into(holder.picture);
        }
        holder.name.setText(news.getName());
        holder.feedback.setText(news.getFeedback());
        holder.date.setText(news.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView name, date, feedback;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            picture = itemView.findViewById(R.id.picture);
            feedback = itemView.findViewById(R.id.feedback);
            date = itemView.findViewById(R.id.date);
        }
    }
}