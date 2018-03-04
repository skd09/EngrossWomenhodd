package com.sharvari.engrosswomenhodd.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharvari.engrosswomenhodd.Pojos.News;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    private ArrayList<News> newsArrayList = new ArrayList<>();
    private Context context;

    public NewsAdapter(ArrayList<News> newsArrayList, Context context) {
        this.newsArrayList = newsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_news_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        Glide.with(context).load(news.getUrl()).into(holder.picture);
        holder.title.setText(news.getTitle());
        holder.story.setText(news.getStory());
        holder.date.setText(news.getDate());
        Log.i("12345", "onBindViewHolder: "+news.getUrl());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView title, story, date;

        public MyViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            title = itemView.findViewById(R.id.title);
            story = itemView.findViewById(R.id.story);
            date = itemView.findViewById(R.id.date);
        }
    }
}
