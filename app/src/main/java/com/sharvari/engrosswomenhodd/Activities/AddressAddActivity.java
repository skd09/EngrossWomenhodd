package com.sharvari.engrosswomenhodd.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Pojos.Address;
import com.sharvari.engrosswomenhodd.Pojos.home;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.InsertAddressRequest;
import com.sharvari.engrosswomenhodd.Requests.UpdateAddressRequest;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressData;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
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
 * Created by sharvari on 05-Mar-18.
 */

public class AddressAddActivity extends AppCompatActivity{
    private EditText type, address, landmark, area, city, pincode, country;
    private Button add;
    private RelativeLayout layout;
    private SharedPreference preference;
    private String addressType;
    private Address a;
    private Loader loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        preference = new SharedPreference(this);
        loader = new Loader(this);

        addressType = getIntent().getExtras().getString("Type");

        type = findViewById(R.id.type);
        address = findViewById(R.id.address);
        landmark = findViewById(R.id.landmark);
        area = findViewById(R.id.area);
        city = findViewById(R.id.city);
        pincode = findViewById(R.id.pincode);
        country = findViewById(R.id.country);
        add = findViewById(R.id.add);
        layout = findViewById(R.id.layout);
        /*Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show();*/

        if(addressType.equals("Update")){
            Bundle data = getIntent().getExtras();
            a = (Address) data.getParcelable("Address");
            setData();
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    private void setData(){

        type.setText(a.getType());
        address.setText(a.getLine1());
        landmark.setText(a.getLandmark());
        area.setText(a.getArea());
        city.setText(a.getCity());
        pincode.setText(a.getPincode());
        country.setText(a.getCountry());

    }

    private void validateData(){
        if(type.getText().toString().isEmpty()){
            Snackbar.make(layout, "Enter type of address",Snackbar.LENGTH_SHORT).show();
            return;
        }else if(address.getText().toString().isEmpty()){
            Snackbar.make(layout, "Enter flat no/ buldg no/ street",Snackbar.LENGTH_SHORT).show();
            return;
        }else if(landmark.getText().toString().isEmpty()){
            Snackbar.make(layout, "Enter landmark",Snackbar.LENGTH_SHORT).show();
            return;
        }else if(area.getText().toString().isEmpty()){
            Snackbar.make(layout, "Enter area",Snackbar.LENGTH_SHORT).show();
            return;
        }else if(city.getText().toString().isEmpty()){
            Snackbar.make(layout, "Enter city",Snackbar.LENGTH_SHORT).show();
            return;
        }else if(pincode.getText().toString().isEmpty()){
            Snackbar.make(layout, "Enter pincode",Snackbar.LENGTH_SHORT).show();
            return;
        }else if(country.getText().toString().isEmpty()){
            Snackbar.make(layout, "Enter country",Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(addressType.equals("New")) {
            insertAddress();
        }else if(addressType.equals("Update")){
            updateAddress();
        }
    }


    private void updateAddress(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        UpdateAddressRequest request = new UpdateAddressRequest(
                a.getTaskId(),
                preference.getUserId(),
                type.getText().toString(),
                address.getText().toString(),
                landmark.getText().toString(),
                area.getText().toString(),
                city.getText().toString(),
                pincode.getText().toString(),
                country.getText().toString()
        );

        Call<UploadFeedbackResponse> call = client.updateAddress(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    Toast.makeText(AddressAddActivity.this, "Address updated against your profile.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }


    private void insertAddress(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        InsertAddressRequest request = new InsertAddressRequest(
                preference.getUserId(),
                type.getText().toString(),
                address.getText().toString(),
                landmark.getText().toString(),
                area.getText().toString(),
                city.getText().toString(),
                pincode.getText().toString(),
                country.getText().toString()
        );

        Call<UploadFeedbackResponse> call = client.insertAddress(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    Toast.makeText(AddressAddActivity.this, "Address updated against your profile.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

}
