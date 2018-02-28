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

import com.sharvari.engrosswomenhodd.Adapters.HomeAdapter;
import com.sharvari.engrosswomenhodd.Pojos.home;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class HomeFragment extends Fragment {

    private HomeAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<home> homeArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView= v.findViewById(R.id.recycler_view);
        adapter = new HomeAdapter(homeArrayList, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareData();

        return v;
    }

    private void prepareData(){
        home h = new home();
        homeArrayList.add(h);

        h = new home();
        homeArrayList.add(h);

        h = new home();
        homeArrayList.add(h);

        h = new home();
        homeArrayList.add(h);

        h = new home();
        homeArrayList.add(h);
        h = new home();
        homeArrayList.add(h);

        h = new home();
        homeArrayList.add(h);

        h = new home();
        homeArrayList.add(h);

        h = new home();
        homeArrayList.add(h);

        adapter.notifyDataSetChanged();
    }

}