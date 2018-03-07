package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Category;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.RealmResults;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class UploadTaskFragment extends Fragment {

    private Spinner typeSpinner,locationSpinner,categorySpinner;
    private SharedPreference preference;
    private EditText title, description, url, pay, date, other;
    private TextView c;
    private String taskType = "Business";
    private RealmResults<UserDetails> userAddress;
    private RealmResults<Category> categories;
    private Users details;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upload_task, container, false);

        typeSpinner = v.findViewById(R.id.typeSpinner);
        locationSpinner = v.findViewById(R.id.locationSpinner);
        categorySpinner = v.findViewById(R.id.categorySpinner);

        title = v.findViewById(R.id.title);
        description = v.findViewById(R.id.description);
        url = v.findViewById(R.id.url);
        pay = v.findViewById(R.id.pay);
        date = v.findViewById(R.id.date);
        c = v.findViewById(R.id.c);
        other = v.findViewById(R.id.other);

        preference= new SharedPreference(getContext());

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


        if(details.getAccountType().equals("Business")){
            array.add("Business");
            onBusinessSelect();
        }else if(details.getAccountType().equals("Both")){
            array.add("Business");
            array.add("Personal");
            onBusinessSelect();
        }else if(details.getAccountType().equals("Personal")){
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
               if(categories.get(position).getName().equals("Others")) {
                   other.setVisibility(View.VISIBLE);
                   c.setVisibility(View.VISIBLE);
               }else {
                   other.setVisibility(View.GONE);
                   c.setVisibility(View.GONE);
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

    public void onPersonalSelect(){
        url.setVisibility(View.GONE);
        categorySpinner.setVisibility(View.GONE);
        c.setVisibility(View.GONE);

        pay.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
    }

    public void onBusinessSelect(){
        pay.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        title.setVisibility(View.GONE);

        url.setVisibility(View.VISIBLE);
        categorySpinner.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
    }

    private void onPersonalUploadClick(){
        Task task = new Task();
        task.setTaskId(UUID.randomUUID().toString());
        task.setUserId(preference.getUserId());
        task.setTitle(title.getText().toString());
        task.setDescription(description.getText().toString());
        task.setAddressId(userAddress.get(locationSpinner.getSelectedItemPosition()).getUserDetailsId());
        task.setPrice(pay.getText().toString());
        task.setIsComplete("0");
        task.setDate(System.currentTimeMillis());
        task.setUpdatedOn(System.currentTimeMillis());
        task.setCreatedOn(System.currentTimeMillis());

        RealmController.with(this).insertTask(task);
        Toast.makeText(getContext(), "Your task is uploaded.", Toast.LENGTH_SHORT).show();
    }

    private void onBusinessUploadClick(){

    }
}