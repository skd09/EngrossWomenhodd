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
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RecyclerTouchListener;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by sharvaridivekar on 08/03/18.
 */

public class MyTaskRequestActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private MyTaskRequestAdapter adapter;
    private ArrayList<Request> requests = new ArrayList<>();
    private String taskId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_task);

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
                int code = RealmController.with(MyTaskRequestActivity.this).acceptRequest(requests.get(position).getId(),requests.get(position).getUserId());
                if(code == 1)
                    Toast.makeText(MyTaskRequestActivity.this, "Request approved.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MyTaskRequestActivity.this, "one request already approved.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
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

    private void prepareData(){

        RealmResults<TaskRequest> r = RealmController.with(this).getRequest(taskId);

        for(TaskRequest t : r){
            Users users = RealmController.with(this).getCustomerDetails(t.getUserId());
            int requestCount = RealmController.with(this).getRequestCount(t.getTaskId());
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
        if(requests.size() == 0){
            Toast.makeText(this, "No request yet against this task.", Toast.LENGTH_SHORT).show();
        }
    }
}
