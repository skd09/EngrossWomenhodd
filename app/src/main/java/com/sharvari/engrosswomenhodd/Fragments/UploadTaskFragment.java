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
import com.sharvari.engrosswomenhodd.Realm.Category;
import com.sharvari.engrosswomenhodd.Realm.Task;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmResults;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class UploadTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private Spinner typeSpinner,locationSpinner,categorySpinner;
    private SharedPreference preference;
    private EditText title, description, url, pay, date, other;
    private TextView c;
    private String taskType = "Personal";
    private RealmResults<UserDetails> userAddress;
    private RealmResults<Category> categories;
    private Users details;
    private Calendar myCalendar = Calendar.getInstance();

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

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), UploadTaskFragment.this, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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


        if(details.getAccountType().equals("Entrepreneur")){
            array.add("Entrepreneur");
            onBusinessSelect();
        }else if(details.getAccountType().equals("Both")){
            array.add("Personal");
            array.add("Entrepreneur");
            onPersonalSelect();
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
               }else {
                   other.setVisibility(View.GONE);
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


        categorySpinner.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        pay.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
    }

    public void onBusinessSelect(){
        pay.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        url.setVisibility(View.VISIBLE);

        categorySpinner.setVisibility(View.GONE);
        c.setVisibility(View.GONE);
    }

    private void onPersonalUploadClick(){

        String[] dFormat = date.getText().toString().split("/");
        Date d = new Date(dFormat[1]+"/"+dFormat[0]+"/"+dFormat[2]);

        Task task = new Task();
        task.setTaskId(UUID.randomUUID().toString());
        task.setUserId(preference.getUserId());
        task.setTitle(title.getText().toString());
        task.setDescription(description.getText().toString());
        task.setAddressId(userAddress.get(locationSpinner.getSelectedItemPosition()).getUserDetailsId());
        task.setPrice(pay.getText().toString());
        task.setIsComplete("0");
        task.setCategoryId(categories.get(categorySpinner.getSelectedItemPosition()).getCategoryId());
        task.setDate(d.getTime());
        task.setUpdatedOn(System.currentTimeMillis());
        task.setCreatedOn(System.currentTimeMillis());

        RealmController.with(this).insertTask(task);
        Toast.makeText(getContext(), "Your task is uploaded.", Toast.LENGTH_SHORT).show();


        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, new UploadTaskFragment());
        transaction.commit();
    }

    private void onBusinessUploadClick(){

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Log.i("Date", "onDateSet: "+i+"/"+i1+"/"+i2);
        String d = (i2+"").toString().length() == 1 ? "0"+i2 : i2+"";
        String m = ((i1+1)+"").toString().length() == 1 ? "0"+(i1+1) : (i1+1)+"";
        date.setText(d+"/"+m+"/"+i);
    }
}