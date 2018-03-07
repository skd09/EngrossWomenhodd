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
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.TaskRequest;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.RealmResults;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        t.setTitle("Request");
        //t.setNavigationIcon(R.drawable.ic_left_arrow_white);

        userId = getIntent().getExtras().getString("UserId");
        taskId = getIntent().getExtras().getString("TaskId");
        userLocation = getIntent().getExtras().getString("Location");

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
        Task task = RealmController.with(this).getTask(taskId);
        Users users = RealmController.with(this).getCustomerDetails(userId);
        UserDetails addressDetails = RealmController.with(this).getUserAddressDetails(task.getAddressId());

        String date = task.getDate()!=null ? Generic.with(this).getDaysLeft(System.currentTimeMillis(),task.getDate()) : "";
        String area = addressDetails!=null ? addressDetails.getArea()+", "+addressDetails.getCountry() : "";
        int request = RealmController.with(this).getRequestCount(task.getTaskId());


        name.setText(users.getFullName());
        leftDays.setText(date);
        requestCount.setText(request+" Request");
        description.setText(task.getDescription());
        amount.setText(task.getPrice());
        location.setText(area);
        title.setText(task.getTitle());
        if(users.getPicture().equals("1")){
            picture.setImageDrawable(this.getResources().getDrawable(R.drawable.img_teenager));
        }else if(users.getPicture().equals("2")){
            picture.setImageDrawable(this.getResources().getDrawable(R.drawable.img_women));
        }else if(users.getPicture().equals("3")){
            picture.setImageDrawable(this.getResources().getDrawable(R.drawable.img_old));
        }
    }

    private void prepareData(){

        RealmResults<TaskRequest> r = RealmController.with(this).getRequest(taskId);

        for(TaskRequest t : r){
            Users users = RealmController.with(this).getCustomerDetails(t.getUserId());
            Request request = new Request(
                    t.getTaskId(),
                    t.getUserId(),
                    users.getFullName(),
                    t.getIsAccepted(),
                    Generic.with(this).dateFormat(t.getCreatedOn()),
                    t.getPrice(),
                    t.getComments(),
                    users.getPicture()

            );
            requests.add(request);

        }

        adapter.notifyDataSetChanged();
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
                TaskRequest request = new TaskRequest(
                        UUID.randomUUID().toString(),
                        taskId,
                        userId,
                        charges.getText().toString(),
                        why.getText().toString(),
                        "0",
                        "0",
                        System.currentTimeMillis(),
                        System.currentTimeMillis()
                );
                int code = RealmController.with(TaskActivity.this).insertTaskRequest(request);
                if(code == 1) {
                    Toast.makeText(TaskActivity.this, "Your request is sent.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TaskActivity.this, "Your request already exist.", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(TaskActivity.this, TaskActivity.class);
                i.putExtra("UserId",userId);
                i.putExtra("TaskId",taskId);
                i.putExtra("Location",userLocation);
                startActivity(i);
                finish();
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

}
