package com.sharvari.engrosswomenhodd.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Adapters.RequestAdapter;
import com.sharvari.engrosswomenhodd.Pojos.Request;
import com.sharvari.engrosswomenhodd.Pojos.home;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.TaskRequest;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.GetTaskHelpRequest;
import com.sharvari.engrosswomenhodd.Requests.InsertTaskHelpRequest;
import com.sharvari.engrosswomenhodd.Response.TaskRequest.TaskRequestData;
import com.sharvari.engrosswomenhodd.Response.TaskRequest.TaskRequestResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvaridivekar on 02/03/18.
 */

public class TaskActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ImageView picture;
    private TextView name, leftDays, requestCount, title, description, location,amount;
    private RequestAdapter adapter;
    private ArrayList<Request> requests = new ArrayList<>();
    private String userId,taskId;
    private String userLocation;
    private home user;
    public Loader loader;
    public SharedPreference preference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        loader= new  Loader(this);
        preference= new  SharedPreference(this);
        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle data = getIntent().getExtras();
        user = (home) data.getParcelable("home");


        t.setTitle("Request");
        //t.setNavigationIcon(R.drawable.ic_left_arrow_white);

        userId = user.getUserId();
        taskId = user.getId();
        userLocation = user.getLocation();

        t.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        picture = findViewById(R.id.picture);
        name = findViewById(R.id.name);
        leftDays = findViewById(R.id.leftDays);
        requestCount = findViewById(R.id.requestCount);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        location = findViewById(R.id.location);
        amount = findViewById(R.id.amount);


        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RequestAdapter(requests, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        setData();
        prepareData();

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLangDialog();
            }
        });
    }

    private void setData(){

        name.setText(user.getName());
        leftDays.setText(user.getLeftDays());
        requestCount.setText(user.getRequest());
        description.setText(user.getDescription());
        amount.setText(user.getAmount());
        location.setText(user.getLocation());
        title.setText(user.getTitle());
        if(user.getPicture().equals("1")){
            picture.setImageDrawable(this.getResources().getDrawable(R.drawable.img_teenager));
        }else if(user.getPicture().equals("2")){
            picture.setImageDrawable(this.getResources().getDrawable(R.drawable.img_women));
        }else if(user.getPicture().equals("3")){
            picture.setImageDrawable(this.getResources().getDrawable(R.drawable.img_old));
        }
    }

    private void prepareData(){
        requests.clear();
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskHelpRequest request = new GetTaskHelpRequest(user.getId());

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

                    }
                }
            }

            @Override
            public void onFailure(Call<TaskRequestResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });

    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.popup_request, null);
        dialogBuilder.setView(dialogView);

        final EditText charges = (EditText) dialogView.findViewById(R.id.charges);
        final EditText why = (EditText) dialogView.findViewById(R.id.why);

        dialogBuilder.setTitle("Want to request?");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                sendRequest(charges.getText().toString(),why.getText().toString());
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

    private void sendRequest(String price, String comments){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        InsertTaskHelpRequest request = new InsertTaskHelpRequest(
                user.getId(),
                preference.getUserId(),
                price,
                comments

        );

        Call<UploadFeedbackResponse> call = client.insertTaskHelpRequest(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    prepareData();
                    Toast.makeText(TaskActivity.this, "Request succesfully sent.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TaskActivity.this, response.body().getStatusMsg(), Toast.LENGTH_SHORT).show();
                }
                loader.hideLoader();
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
