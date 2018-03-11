package com.sharvari.engrosswomenhodd.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Adapters.MyTaskRequestAdapter;
import com.sharvari.engrosswomenhodd.Adapters.RequestAdapter;
import com.sharvari.engrosswomenhodd.Pojos.Request;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.TaskRequest;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.AcceptTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.GetTaskHelpRequest;
import com.sharvari.engrosswomenhodd.Response.TaskRequest.TaskRequestData;
import com.sharvari.engrosswomenhodd.Response.TaskRequest.TaskRequestResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RecyclerTouchListener;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;

import java.util.ArrayList;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvaridivekar on 08/03/18.
 */

public class MyTaskRequestActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private MyTaskRequestAdapter adapter;
    private ArrayList<Request> requests = new ArrayList<>();
    private String taskId;
    private Loader loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_task);

        loader = new Loader(this);
        taskId = getIntent().getExtras().getString("TaskId");

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MyTaskRequestAdapter(requests, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                onRequestClick(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareData();
    }

    public void onRequestClick(final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Alert");
        dialogBuilder.setMessage("Are you sure you want to accept this request?");

        dialogBuilder.setTitle("Want to request?");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                acceptRequest(position);
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }

    private void prepareData(){
        requests.clear();
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskHelpRequest request = new GetTaskHelpRequest(taskId);

        Call<TaskRequestResponse> call = client.getTaskRequest(request);

        call.enqueue(new Callback<TaskRequestResponse>() {
            @Override
            public void onResponse(Call<TaskRequestResponse> call, Response<TaskRequestResponse> response) {
                if(response.body().getStatusCode().equals("0")) {
                    loader.hideLoader();
                    if (response.body().getTaskList().size() > 0) {
                        ArrayList<TaskRequestData> data = response.body().getTaskList();

                        for (TaskRequestData t : data) {
                            Request request = new Request(
                                    t.getTaskId(),
                                    t.getUserId(),
                                    t.getFullName(),
                                    t.getIsAccepted().equals("F") ? "PENDING" : "ACCEPTED",
                                    dateFormat(t.getCreatedOn()),
                                    t.getPrice(),
                                    t.getComments(),
                                    t.getPicture(),
                                    t.getTaskRequestId()

                            );
                            requests.add(request);
                        }
                        adapter.notifyDataSetChanged();

                    }else{
                        Toast.makeText(MyTaskRequestActivity.this, "No request yet registered against this task.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskRequestResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });

    }

    private void acceptRequest(final int position){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        AcceptTaskRequest request = new AcceptTaskRequest(requests.get(position).getTaskDetailsId());

        Call<UploadFeedbackResponse> call = client.acceptTaskRequest(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                if(response.body().getStatusCode().equals("0")) {
                    loader.hideLoader();
                    Toast.makeText(MyTaskRequestActivity.this, "Request approved.", Toast.LENGTH_SHORT).show();
                    prepareData();
                }
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
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
