package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Activities.LoginActivity;
import com.sharvari.engrosswomenhodd.Adapters.HomeAdapter;
import com.sharvari.engrosswomenhodd.Pojos.home;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.UserRegisterRequest;
import com.sharvari.engrosswomenhodd.Response.GetTaskResponse.GetTaskData;
import com.sharvari.engrosswomenhodd.Response.GetTaskResponse.GetTaskresponse;
import com.sharvari.engrosswomenhodd.Response.UserRegisterResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class HomeFragment extends Fragment {

    private HomeAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<home> homeArrayList = new ArrayList<>();
    private BannerSlider bannerSlider;
    private ImageView downArrow;
    private LinearLayout layout_distance;
    private SharedPreference preference;
    private Loader loader;
    private SwipeRefreshLayout swiperefresh;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView= v.findViewById(R.id.recycler_view);
        loader = new Loader(getContext());
        adapter = new HomeAdapter(homeArrayList, getContext());
        preference = new SharedPreference(getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
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

        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        bannerSlider = v.findViewById(R.id.banner);
        layout_distance = v.findViewById(R.id.layout_distance);

        prepareData();

        downArrow = v.findViewById(R.id.img);
        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(layout_distance.getVisibility() == View.VISIBLE){
                layout_distance.setVisibility(View.GONE);
            }else{
                layout_distance.setVisibility(View.VISIBLE);
            }
            }
        });
        slider();
        return v;
    }

    private void slider(){
        List<Banner> banners=new ArrayList<>();
        banners.add(new RemoteBanner("https://i.pinimg.com/564x/5c/21/c2/5c21c2a2a85349377a15d7db98bc83ca.jpg"));
        banners.add(new RemoteBanner("https://i.pinimg.com/564x/c6/b7/75/c6b77598132d589c298e09fdaac34c61.jpg"));
        banners.add(new RemoteBanner("https://i.pinimg.com/564x/df/2a/cc/df2acc48779e5e67e437654e1d93e13e.jpg"));
        banners.add(new RemoteBanner("https://i.pinimg.com/564x/1c/df/e5/1cdfe582fd28f2f810c7285312ad152b.jpg"));
        bannerSlider.setBanners(banners);
    }

    private void prepareData(){
        homeArrayList.clear();
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskRequest request = new GetTaskRequest(preference.getUserId());

        Call<GetTaskresponse> call = client.getTask(request);

        call.enqueue(new Callback<GetTaskresponse>() {
            @Override
            public void onResponse(Call<GetTaskresponse> call, Response<GetTaskresponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    if(response.body().getData().size()>0){
                        ArrayList<GetTaskData> data = response.body().getData();
                        for(GetTaskData t : data){
                            home h = new home(t.getTaskId(),t.getPicture(),t.getFullName(),dateFormat(t.getCreatedOn()),
                                    "",t.getTitle(),t.getDescription(),t.getPrice(),
                                    t.getArea()+", "+t.getCountry(),t.getUserId());
                            homeArrayList.add(h);
                        }

                        adapter.notifyDataSetChanged();

                    }else{
                        Toast.makeText(getContext(), "No task uploaded", Toast.LENGTH_SHORT).show();
                    }
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetTaskresponse> call, Throwable t) {
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