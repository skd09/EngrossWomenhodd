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

import com.sharvari.engrosswomenhodd.Adapters.SeeFeedbackAdapter;
import com.sharvari.engrosswomenhodd.Pojos.SeeFeedback;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Feedback;
import com.sharvari.engrosswomenhodd.Utils.RealmController;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class SeeFeedbackFragment extends Fragment {
    private SeeFeedbackAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<SeeFeedback> arrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_see_feedback, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new SeeFeedbackAdapter(arrayList, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        getFeedback();

        return v;
    }

    public void getFeedback(){
        ArrayList<SeeFeedback> feedback = RealmController.with(this).getFeedbackList();
        arrayList.addAll(feedback);
        adapter.notifyDataSetChanged();
    }

}
