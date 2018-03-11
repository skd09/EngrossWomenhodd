package com.sharvari.engrosswomenhodd.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Fragments.UploadTaskFragment;
import com.sharvari.engrosswomenhodd.Pojos.MyTask;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Category;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.UpdateMyTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.UploadUserTaskRequest;
import com.sharvari.engrosswomenhodd.Response.Category.GetCategoryData;
import com.sharvari.engrosswomenhodd.Response.Category.GetCategoryResponse;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressData;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Response.UserDetails.GetUserData;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Generic;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvaridivekar on 08/03/18.
 */

public class UpdateMyTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner typeSpinner,locationSpinner,categorySpinner;
    private SharedPreference preference;
    private EditText title, description, url, pay, date, other, completed;
    private TextView c;
    private String taskType = "Personal";
    private MyTask task;
    private RatingBar ratingBar;
    private Calendar myCalendar = Calendar.getInstance();
    private ArrayList<String> array = new ArrayList<>();
    private ArrayList<String> locationArray = new ArrayList<>();
    private ArrayList<String> categoryArray = new ArrayList<>();
    private Loader loader;

    private ArrayList<GetCategoryData> categoryData;
    private ArrayList<GetAddressData> userData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_task);

        loader = new Loader(this);

        Bundle data = getIntent().getExtras();
        task = (MyTask) data.getParcelable("Task");
        preference= new SharedPreference(this);
        getLocation();
        getCategory();

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
                if(categoryData!=null) {
                    if (categoryData.get(position).getName().equals("Others")) {
                        if (categoryData != null) {
                            other.setVisibility(View.VISIBLE);
                        } else {
                            other.setVisibility(View.GONE);
                        }
                    }
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



    private void getLocation(){
        locationArray.add("Select");
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskRequest request = new GetTaskRequest(preference.getUserId());

        Call<GetAddressResponse> call = client.getAddresses(request);

        call.enqueue(new Callback<GetAddressResponse>() {
            @Override
            public void onResponse(Call<GetAddressResponse> call, Response<GetAddressResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    userData = response.body().getData();
                    if(userData.size()>0) {

                        for(GetAddressData d : userData){
                            locationArray.add(d.getAddressType());
                        }

                    }
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetAddressResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    private void getCategory(){
        categoryArray.add("Select");
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        Call<GetCategoryResponse> call = client.getCategory();

        call.enqueue(new Callback<GetCategoryResponse>() {
            @Override
            public void onResponse(Call<GetCategoryResponse> call, Response<GetCategoryResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    categoryData = response.body().getData();
                    if(categoryData.size()>0) {

                        for(GetCategoryData d : categoryData){
                            categoryArray.add(d.getName());
                        }

                    }
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetCategoryResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    public void setData(){
        description.setText(task.getDescription());
        pay.setText(task.getAmount());
        title.setText(task.getTitle());
        date.setText(task.getDate());
        completed.setText(task.getStatus().equals("PENDING") ? "No" : "Yes");
        int pos = 0;
        if(locationArray.size()>1){
            for(GetAddressData data : userData){
                pos++;
                if(data.getUserDetailsId() == task.getAddressId()) {
                    locationSpinner.setSelection(pos);
                    break;
                }
            }

        }
    }

    public void onPersonalUploadClick(){
        String[] dFormat = date.getText().toString().split("/");
        Date d = new Date(dFormat[1]+"/"+dFormat[0]+"/"+dFormat[2]);

        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        UpdateMyTaskRequest request = new UpdateMyTaskRequest(
                task.getId(),
                userData.get(locationSpinner.getSelectedItemPosition()-1).getUserDetailsId(),
                title.getText().toString(),
                description.getText().toString(),
                pay.getText().toString(),
                d.toString(),
                categoryData.get(categorySpinner.getSelectedItemPosition()-1).getCategoryId(),
                completed.getText().toString().toLowerCase().equals("no") ? "F":"T",
                ratingBar.getRating()+""
        );

        Call<UploadFeedbackResponse> call = client.updateMyTaskRequest(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                loader.hideLoader();
                if(response.body().getStatusCode().equals("0")){
                   finish();
                    Toast.makeText(UpdateMyTaskActivity.this, "Updated youra task details.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String d = (i2+"").toString().length() == 1 ? "0"+i2 : i2+"";
        String m = ((i1+1)+"").toString().length() == 1 ? "0"+(i1+1) : (i1+1)+"";
        date.setText(d+"/"+m+"/"+i);
    }
}
