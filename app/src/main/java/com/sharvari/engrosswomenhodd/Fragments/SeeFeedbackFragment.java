package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Adapters.SeeFeedbackAdapter;
import com.sharvari.engrosswomenhodd.Pojos.SeeFeedback;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Feedback;
import com.sharvari.engrosswomenhodd.Requests.UploadFeedbackRequest;
import com.sharvari.engrosswomenhodd.Response.Feedback.GetFeedbackList;
import com.sharvari.engrosswomenhodd.Response.Feedback.GetFeedbackResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class SeeFeedbackFragment extends Fragment {
    private SeeFeedbackAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<SeeFeedback> arrayList = new ArrayList<>();
    private Loader loader;
    private SwipeRefreshLayout swiperefresh;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_see_feedback, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        loader = new Loader(getContext());
        adapter = new SeeFeedbackAdapter(arrayList, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        swiperefresh = v.findViewById(R.id.swiperefresh);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFeedback();
                swiperefresh.setRefreshing(false);
            }
        });

        getFeedback();

        return v;
    }

    public void getFeedback(){
        arrayList.clear();
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);
        Call<GetFeedbackResponse> call = client.getFeedback();

        call.enqueue(new Callback<GetFeedbackResponse>() {
            @Override
            public void onResponse(Call<GetFeedbackResponse> call, Response<GetFeedbackResponse> response) {
                if(response.body()!=null){
                    if( response.body().getList().size()==0 ) return;

                    ArrayList<GetFeedbackList> list = response.body().getList();
                    for(GetFeedbackList feedbacks : list){
                        SeeFeedback f = new SeeFeedback("Anonymous",dateFormat(feedbacks.getCreatedOn()),
                                feedbacks.getMessage(),"");
                        arrayList.add(f);

                    }
                    adapter.notifyDataSetChanged();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetFeedbackResponse> call, Throwable t) {
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
