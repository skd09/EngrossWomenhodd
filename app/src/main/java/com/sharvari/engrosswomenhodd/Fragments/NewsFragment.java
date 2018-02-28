package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sharvari.engrosswomenhodd.Adapters.NewsAdapter;
import com.sharvari.engrosswomenhodd.Pojos.News;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class NewsFragment extends Fragment {

    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<News> arrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new NewsAdapter(arrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareData();

        return v;
    }

    private void prepareData() {

        News n = new News();
        arrayList.add(n);

        n = new News();
        arrayList.add(n);

        n = new News();
        arrayList.add(n);

        n = new News();
        arrayList.add(n);

        n = new News();
        arrayList.add(n);

        n = new News();
        arrayList.add(n);

        n = new News();
        arrayList.add(n);

        n = new News();
        arrayList.add(n);

        n = new News();
        arrayList.add(n);

        adapter.notifyDataSetChanged();
    }

}