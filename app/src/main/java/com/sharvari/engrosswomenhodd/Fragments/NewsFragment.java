package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.sharvari.engrosswomenhodd.Adapters.NewsAdapter;
import com.sharvari.engrosswomenhodd.Pojos.News;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.NewsFeed;
import com.sharvari.engrosswomenhodd.Requests.UploadNewsRequest;
import com.sharvari.engrosswomenhodd.Response.News.NewsList;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    private Loader loader;
    private SwipeRefreshLayout swiperefresh;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        loader = new Loader(getContext());

        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new NewsAdapter(arrayList, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        swiperefresh = v.findViewById(R.id.swiperefresh);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareData();
                swiperefresh.setRefreshing(false);
            }
        });


        bannerSlider = v.findViewById(R.id.banner);
        slider();

        prepareData();

        return v;
    }

    private void slider(){
        List<Banner> banners=new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.img_bg));
        banners.add(new RemoteBanner("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFoQUKRYF-46JVh48z8w2eZhngXTSvnSOECV0SplJmtYOFAfKhRQ"));
        bannerSlider.setBanners(banners);
    }

    private void prepareData(){
        arrayList.clear();
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        Call<com.sharvari.engrosswomenhodd.Response.News.News> call = client.getNews();

        call.enqueue(new Callback<com.sharvari.engrosswomenhodd.Response.News.News>() {
            @Override
            public void onResponse(Call<com.sharvari.engrosswomenhodd.Response.News.News> call, Response<com.sharvari.engrosswomenhodd.Response.News.News> response) {
                if(response.body().getList().size()>0){

                    ArrayList<NewsList> list = response.body().getList();

                    for (NewsList news : list) {

                        News n = new News(news.getTitle(), news.getStory(), news.getPicture(), dateFormat(news.getCreatedOn()));
                        arrayList.add(n);

                    }
                    adapter.notifyDataSetChanged();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<com.sharvari.engrosswomenhodd.Response.News.News> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    private String dateFormat(String dateStr){

        String[] format = dateStr.split(" ")[0].split("-");

        String formatedDate = format[2] + "/" + format[1] + "/" + format[0];
        return formatedDate;
    }

}