package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Adapters.BusinessAdapter;
import com.sharvari.engrosswomenhodd.Adapters.MyTaskAdapter;
import com.sharvari.engrosswomenhodd.Pojos.Business;
import com.sharvari.engrosswomenhodd.Pojos.MyTask;
import com.sharvari.engrosswomenhodd.Pojos.home;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Response.GetTaskResponse.GetTaskData;
import com.sharvari.engrosswomenhodd.Response.GetTaskResponse.GetTaskresponse;
import com.sharvari.engrosswomenhodd.Response.MyTask.GetMyTaskData;
import com.sharvari.engrosswomenhodd.Response.MyTask.GetMyTaskResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class MyTaskFragment extends Fragment {

    private MyTaskAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<MyTask> arrayList = new ArrayList<>();
    private SharedPreference preference;
    private Loader loader;
    private SwipeRefreshLayout swiperefresh;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_task, container, false);

        preference = new SharedPreference(getContext());
        loader = new Loader(getContext());

        swiperefresh = v.findViewById(R.id.swiperefresh);
        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new MyTaskAdapter(arrayList, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareData();
                swiperefresh.setRefreshing(false);
            }
        });

        prepareData();

        return v;
    }

    private void prepareData(){
        arrayList.clear();
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskRequest request = new GetTaskRequest(preference.getUserId());

        Call<GetMyTaskResponse> call = client.getMyTask(request);

        call.enqueue(new Callback<GetMyTaskResponse>() {
            @Override
            public void onResponse(Call<GetMyTaskResponse> call, Response<GetMyTaskResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    if(response.body().getData().size()>0){
                        ArrayList<GetMyTaskData> data = response.body().getData();
                        for(GetMyTaskData t : data){
                            MyTask n = new MyTask(t.getTaskId(),t.getFullName(),
                                    dateFormat(t.getCreatedOn()),t.getDescription(),
                                    t.getIsComplete().equals("F") ? "PENDING" : "COMPLETED",
                                    t.getArea()+", "+t.getCountry(), t.getPrice(),"0",t.getTitle(),t.getAddressId());
                            arrayList.add(n);
                        }

                        adapter.notifyDataSetChanged();

                    }else{
                        Toast.makeText(getContext(), "No task uploaded", Toast.LENGTH_SHORT).show();
                    }
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetMyTaskResponse> call, Throwable t) {
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