package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sharvari.engrosswomenhodd.Adapters.HomeAdapter;
import com.sharvari.engrosswomenhodd.Pojos.home;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmResults;
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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView= v.findViewById(R.id.recycler_view);
        adapter = new HomeAdapter(homeArrayList, getContext());
        preference = new SharedPreference(getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        bannerSlider = v.findViewById(R.id.banner);
        layout_distance = v.findViewById(R.id.layout_distance);
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
        prepareData();
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

        RealmResults<Task> task = RealmController.with(this).getAllTask(preference.getUserId());

        for(Task t : task){
            Users users = RealmController.with(this).getCustomerDetails(t.getUserId());
            UserDetails addressDetails = RealmController.with(this).getUserAddressDetails(t.getAddressId());

            String date = t.getCreatedOn()!=null ? dateFormat(t.getCreatedOn()) : "";
            String area = addressDetails!=null ? addressDetails.getArea()+", "+addressDetails.getCountry() : "";
            int request = RealmController.with(this).getRequestCount(t.getTaskId());

            home h = new home(t.getTaskId(),users.getPicture(),users.getFullName(),date,request+" Request",t.getTitle(),t.getDescription(),t.getPrice(),
                    area);
            homeArrayList.add(h);
        }

        adapter.notifyDataSetChanged();
    }
    private String dateFormat(Long date){
        Date d = new Date(date);
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(d);
    }
}