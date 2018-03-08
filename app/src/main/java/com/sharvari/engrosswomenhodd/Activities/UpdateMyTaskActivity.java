package com.sharvari.engrosswomenhodd.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.sharvari.engrosswomenhodd.Fragments.UploadTaskFragment;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Category;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.RealmResults;

/**
 * Created by sharvaridivekar on 08/03/18.
 */

public class UpdateMyTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner typeSpinner,locationSpinner,categorySpinner;
    private SharedPreference preference;
    private EditText title, description, url, pay, date, other, completed;
    private TextView c;
    private String taskType = "Personal";
    private RealmResults<UserDetails> userAddress;
    private RealmResults<Category> categories;
    private Users details;
    private RatingBar ratingBar;
    private Calendar myCalendar = Calendar.getInstance();
    private String taskId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_task);

        taskId = getIntent().getExtras().getString("TaskId");

        typeSpinner = findViewById(R.id.typeSpinner);
        locationSpinner = findViewById(R.id.locationSpinner);
        categorySpinner = findViewById(R.id.categorySpinner);

        ratingBar = findViewById(R.id.ratingBar1);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        url = findViewById(R.id.url);
        pay = findViewById(R.id.pay);
        date = findViewById(R.id.date);
        c = findViewById(R.id.c);
        other = findViewById(R.id.other);
        completed = findViewById(R.id.completed);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UpdateMyTaskActivity.this, UpdateMyTaskActivity.this, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        preference= new SharedPreference(this);

        details = RealmController.with(this).getCustomerDetails(preference.getUserId());
        userAddress = RealmController.with(this).getUserAddress(preference.getUserId());
        categories = RealmController.with(this).getCategory();

        final ArrayList<String> array = new ArrayList<>();
        ArrayList<String> locationArray = new ArrayList<>();
        ArrayList<String> categoryArray = new ArrayList<>();

        for(UserDetails d : userAddress){
            locationArray.add(d.getAddressType());
        }

        for(Category d : categories){
            categoryArray.add(d.getName());
        }

        array.add("Personal");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.layout_spinner,
                array
        );
        typeSpinner.setAdapter(adapter);


        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(
                this,
                R.layout.layout_spinner,
                locationArray
        );
        locationSpinner.setAdapter(locationAdapter);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
                this,
                R.layout.layout_spinner,
                categoryArray
        );
        categorySpinner.setAdapter(categoryAdapter);


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(categories.get(position).getName().equals("Others")) {
                    other.setVisibility(View.VISIBLE);
                }else {
                    other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPersonalUploadClick();
            }
        });
        setData();
    }

    public void setData(){
        Task task = RealmController.with(this).getTask(taskId);
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        pay.setText(task.getPrice());
        title.setText(task.getTitle());
        date.setText(Generic.with(this).dateFormat(task.getDate()));
        completed.setText(task.getIsComplete().equals("0") ? "No" : "Yes");
    }

    public void onPersonalUploadClick(){

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String d = (i2+"").toString().length() == 1 ? "0"+i2 : i2+"";
        String m = ((i1+1)+"").toString().length() == 1 ? "0"+(i1+1) : (i1+1)+"";
        date.setText(d+"/"+m+"/"+i);
    }
}
