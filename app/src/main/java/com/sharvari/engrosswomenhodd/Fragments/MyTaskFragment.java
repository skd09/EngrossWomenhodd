package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sharvari.engrosswomenhodd.Adapters.BusinessAdapter;
import com.sharvari.engrosswomenhodd.Adapters.MyTaskAdapter;
import com.sharvari.engrosswomenhodd.Pojos.Business;
import com.sharvari.engrosswomenhodd.Pojos.MyTask;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmResults;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class MyTaskFragment extends Fragment {

    private MyTaskAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<MyTask> arrayList = new ArrayList<>();
    private SharedPreference preference;
    private Users users;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_task, container, false);

        preference = new SharedPreference(getContext());
        users = RealmController.with(this).getCustomerDetails(preference.getUserId());

        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new MyTaskAdapter(arrayList, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareData();

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void prepareData() {

        RealmResults<Task> task = RealmController.with(this).getMyTask(preference.getUserId());

        for(Task t : task){
            UserDetails d = RealmController.with(this).getUserAddressDetails(t.getAddressId());
            String status = t.getIsComplete().equals("0") ? "PENDING" : "COMPLETE";
            String date = t.getDate()!=null ? Generic.with(this).getDaysLeft(System.currentTimeMillis(),t.getDate()) : "";
            int request = RealmController.with(this).getRequestCount(t.getTaskId());

            MyTask n = new MyTask(t.getTaskId(),users.getFullName(),date,t.getDescription(),
                    "PERSONAL-"+status,d.getArea()+", "+d.getCountry(),t.getPrice(),
                    request+" Requests");
            arrayList.add(n);
        }

        adapter.notifyDataSetChanged();
    }

    private String dateFormat(Long date){
        Date d = new Date(date);
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(d);
    }
}