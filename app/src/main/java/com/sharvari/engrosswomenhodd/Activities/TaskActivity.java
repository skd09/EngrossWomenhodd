package com.sharvari.engrosswomenhodd.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.TextView;

import com.sharvari.engrosswomenhodd.Adapters.RequestAdapter;
import com.sharvari.engrosswomenhodd.Pojos.Request;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 02/03/18.
 */

public class TaskActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RequestAdapter adapter;
    private ArrayList<Request> requests = new ArrayList<>();

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

        t.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RequestAdapter(requests);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareData();

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLangDialog();
            }
        });
    }

    private void prepareData(){
        Request request = new Request();
        requests.add(request);

        request = new Request();
        requests.add(request);

        request = new Request();
        requests.add(request);

        request = new Request();
        requests.add(request);

        request = new Request();
        requests.add(request);

        adapter.notifyDataSetChanged();
    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.popup_request, null);
        dialogBuilder.setView(dialogView);

        /*final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);*/

        dialogBuilder.setTitle("Want to request?");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
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
