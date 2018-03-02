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
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class NewsFragment extends Fragment {

    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<News> arrayList = new ArrayList<>();
    private BannerSlider bannerSlider;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new NewsAdapter(arrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        bannerSlider = v.findViewById(R.id.banner);
        slider();

        prepareData();

        return v;
    }

    private void slider(){
        List<Banner> banners=new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.img_bg));
        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg"));
        bannerSlider.setBanners(banners);
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