package com.sharvari.engrosswomenhodd.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.UploadBusinessRequest;
import com.sharvari.engrosswomenhodd.Requests.UploadUserTaskRequest;
import com.sharvari.engrosswomenhodd.Response.Category.GetCategoryData;
import com.sharvari.engrosswomenhodd.Response.Category.GetCategoryResponse;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressData;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class UploadTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private Spinner typeSpinner,locationSpinner,categorySpinner;
    private SharedPreference preference;
    private EditText title, description, url, pay, date, other, companyName;
    private TextView c,location;
    private String taskType = "Personal";
    private Loader loader;
    private ArrayList<String> categoryArray = new ArrayList<>();
    private ArrayList<String> locationArray = new ArrayList<>();
    private ArrayList<GetCategoryData> categoryData;
    private ArrayList<GetAddressData> userData;


    private Calendar myCalendar = Calendar.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upload_task, container, false);

        loader = new Loader(getContext());

        typeSpinner = v.findViewById(R.id.typeSpinner);
        locationSpinner = v.findViewById(R.id.locationSpinner);
        categorySpinner = v.findViewById(R.id.categorySpinner);

        title = v.findViewById(R.id.title);
        companyName = v.findViewById(R.id.companyName);
        description = v.findViewById(R.id.description);
        url = v.findViewById(R.id.url);
        pay = v.findViewById(R.id.pay);
        date = v.findViewById(R.id.date);
        c = v.findViewById(R.id.c);
        location = v.findViewById(R.id.location);
        other = v.findViewById(R.id.other);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), UploadTaskFragment.this, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        preference= new SharedPreference(getContext());
        getLocation();
        getCategory();

        final ArrayList<String> array = new ArrayList<>();

        String details = preference.getUserType();
        if(details.equals("Entrepreneur")){
            array.add("Entrepreneur");
            onBusinessSelect();
        }else if(details.equals("Both")){
            array.add("Personal");
            array.add("Entrepreneur");
            onPersonalSelect();
        }else if(details.equals("Personal")){
            array.add("Personal");
            onPersonalSelect();
            taskType = "Personal";
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.layout_spinner,
                array
        );
        typeSpinner.setAdapter(adapter);


        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.layout_spinner,
                locationArray
        );
        locationSpinner.setAdapter(locationAdapter);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.layout_spinner,
                categoryArray
        );
        categorySpinner.setAdapter(categoryAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               if(array.get(position).equals("Personal")){
                   onPersonalSelect();
                   taskType = "Personal";
               }else{
                   onBusinessSelect();
                   taskType = "Business";
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(categoryData!=null){
                    if(categoryData.get(position).getName().equals("Others")) {
                        other.setVisibility(View.VISIBLE);
                    }else {
                        other.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        v.findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taskType.equals("Personal")){
                    onPersonalUploadClick();
                }else {
                    onBusinessUploadClick();
                }
            }
        });

        return v;
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

    public void onPersonalSelect(){
        url.setVisibility(View.GONE);
        companyName.setVisibility(View.GONE);


        categorySpinner.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        location.setVisibility(View.VISIBLE);
        locationSpinner.setVisibility(View.VISIBLE);
        pay.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
    }

    public void onBusinessSelect(){
        pay.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        url.setVisibility(View.VISIBLE);
        companyName.setVisibility(View.VISIBLE);

        categorySpinner.setVisibility(View.GONE);
        c.setVisibility(View.GONE);
        location.setVisibility(View.GONE);
        locationSpinner.setVisibility(View.GONE);
    }

    private void onPersonalUploadClick(){

        String[] dFormat = date.getText().toString().split("/");
        Date d = new Date(dFormat[1]+"/"+dFormat[0]+"/"+dFormat[2]);

        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        UploadUserTaskRequest request = new UploadUserTaskRequest(
                preference.getUserId(),
                userData.get(locationSpinner.getSelectedItemPosition()-1).getUserDetailsId(),
                title.getText().toString(),
                description.getText().toString(),
                pay.getText().toString(),
                d.toString(),
                categoryData.get(categorySpinner.getSelectedItemPosition()-1).getCategoryId()

        );

        Call<UploadFeedbackResponse> call = client.uploadTaskRequest(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    Toast.makeText(getContext(), "Your task is uploaded.", Toast.LENGTH_SHORT).show();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, new UploadTaskFragment());
                    transaction.commit();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });

    }

    private void onBusinessUploadClick(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        UploadBusinessRequest request = new UploadBusinessRequest(
                preference.getUserId(),
                companyName.getText().toString(),
                description.getText().toString(),
                url.getText().toString()
        );

        Call<UploadFeedbackResponse> call = client.uploadBusiness(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    Toast.makeText(getContext(), "Your ad is uploaded sucessfully.", Toast.LENGTH_SHORT).show();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, new UploadTaskFragment());
                    transaction.commit();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Log.i("Date", "onDateSet: "+i+"/"+i1+"/"+i2);
        String d = (i2+"").toString().length() == 1 ? "0"+i2 : i2+"";
        String m = ((i1+1)+"").toString().length() == 1 ? "0"+(i1+1) : (i1+1)+"";
        date.setText(d+"/"+m+"/"+i);
    }
}