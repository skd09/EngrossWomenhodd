package com.sharvari.engrosswomenhodd.Activities;

import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Adapters.AddressAdapter;
import com.sharvari.engrosswomenhodd.Pojos.Address;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.UserDetails;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.ChangePassword;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.UpdateUserDetailsRequest;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressData;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Response.UserDetails.GetUserData;
import com.sharvari.engrosswomenhodd.Response.UserDetails.GetUserDetails;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RecyclerTouchListener;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvaridivekar on 02/03/18.
 */

public class EditPersonalDetailsActivity extends AppCompatActivity{

    private LinearLayout layout_profile, layout_password, layout_invite;
    private RelativeLayout layout_address;
    private RecyclerView recyclerView;
    private AddressAdapter adapter;
    private ArrayList<Address> addresses = new ArrayList<>();
    private FloatingActionButton fab;
    private TextView textAddress;
    private EditText name,mobile,skills, about;
    private EditText oldPassword,newPassword, confirmPassword;
    private EditText friendName,email, message;
    private SharedPreference preference;

    private RadioGroup radioGroup;
    private RadioButton personal, business, both;
    private Button update;
    private Loader loader;
    private String password="";
    private GetUserData data;
    private String page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_details);

        loader = new Loader(this);

        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        page = getIntent().getExtras().getString("Page");
        preference = new SharedPreference(this);


        t.setTitle(page);
        //t.setNavigationIcon(R.drawable.ic_left_arrow_white);

        t.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        layout_profile = findViewById(R.id.layout_profile);
        layout_password = findViewById(R.id.layout_password);
        layout_invite = findViewById(R.id.layout_invite);
        layout_address = findViewById(R.id.layout_address);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);
        textAddress = findViewById(R.id.textAddress);
        update = findViewById(R.id.update);

        adapter = new AddressAdapter(addresses);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addresses.size()<3) {
                    Intent i = new Intent(EditPersonalDetailsActivity.this, AddressAddActivity.class);
                    i.putExtra("Type","New");
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Max 3 address can be added.", Toast.LENGTH_LONG).show();
                }
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Address a = addresses.get(position);
                Intent i = new Intent(EditPersonalDetailsActivity.this, AddressAddActivity.class);
                i.putExtra("Type","Update");
                i.putExtra("Address", a);
                finish();
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page.equals("Edit Profile")){
                    updatePersonalDetails();
                }else if(page.equals("Change Password")){
                    if(oldPassword.getText().toString().isEmpty() && newPassword.getText().toString().isEmpty() && confirmPassword.getText().toString().isEmpty()){
                        Toast.makeText(EditPersonalDetailsActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        if(oldPassword.getText().toString().equals(password)){
                            if(newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                                updatePassword();
                                Toast.makeText(EditPersonalDetailsActivity.this, "Login with new password", Toast.LENGTH_SHORT).show();
                                finish();
                                preference.logoutUser();
                            }else{
                                Toast.makeText(EditPersonalDetailsActivity.this, "Confirm Password does not match.", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }else{
                            Toast.makeText(EditPersonalDetailsActivity.this, "Previous password does not match.", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                }else if(page.equals("Update Address")){
                    if(addresses.size()>0){
                        for(int i = 0; i<addresses.size() ;i++){
                            View recycler = recyclerView.getChildAt(i);
                            EditText text = recycler.findViewById(R.id.area);

                            Toast.makeText(EditPersonalDetailsActivity.this, text.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                }else if(page.equals("Invite a Friend")){
                    if(email.getText().toString().isEmpty() && message.getText().toString().isEmpty()){
                        Toast.makeText(EditPersonalDetailsActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto",email.getText().toString(), null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hi,"+friendName.getText()+". This is invite for app which is only for women.");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    }
                }
                Toast.makeText(EditPersonalDetailsActivity.this, "Details updated.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        getUserDetails();
        selectedPage(page);
    }

    private void selectedPage(String page){
        if(page.equals("Edit Profile")){
            layout_address.setVisibility(View.GONE);
            layout_password.setVisibility(View.GONE);
            layout_invite.setVisibility(View.GONE);
            layout_profile.setVisibility(View.VISIBLE);

            name = findViewById(R.id.name);
            mobile = findViewById(R.id.mobile);
            skills = findViewById(R.id.skills);
            about = findViewById(R.id.about);

            radioGroup = findViewById(R.id.radioGroup);
            personal = findViewById(R.id.personal);
            business = findViewById(R.id.business);
            both = findViewById(R.id.both);



        }else if(page.equals("Change Password")){
            layout_address.setVisibility(View.GONE);
            layout_password.setVisibility(View.VISIBLE);
            layout_invite.setVisibility(View.GONE);
            layout_profile.setVisibility(View.GONE);

            oldPassword = findViewById(R.id.oldPassword);
            newPassword = findViewById(R.id.newPassword);
            confirmPassword = findViewById(R.id.confirmPassword);

        }else if(page.equals("Update Address")){
            layout_address.setVisibility(View.VISIBLE);
            layout_password.setVisibility(View.GONE);
            layout_invite.setVisibility(View.GONE);
            layout_profile.setVisibility(View.GONE);
            Toast.makeText(this, "Swipe right to see you address.", Toast.LENGTH_LONG).show();

            update.setVisibility(View.GONE);
            getAddressData();

        }else if(page.equals("Invite a Friend")){
            layout_address.setVisibility(View.GONE);
            layout_password.setVisibility(View.GONE);
            layout_invite.setVisibility(View.VISIBLE);
            layout_profile.setVisibility(View.GONE);

            friendName = findViewById(R.id.friendName);
            email = findViewById(R.id.email);
            message = findViewById(R.id.message);
        }
    }

    private void getAddressData(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskRequest request = new GetTaskRequest(preference.getUserId());

        Call<GetAddressResponse> call = client.getAddresses(request);

        call.enqueue(new Callback<GetAddressResponse>() {
            @Override
            public void onResponse(Call<GetAddressResponse> call, Response<GetAddressResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    ArrayList<GetAddressData> userData = response.body().getData();
                    if(userData.size()>0) {

                        for(GetAddressData d : userData){
                            Address address = new Address(d.getAddressType(),d.getAddress(),d.getLandmark(),
                                    d.getArea(),d.getCity(),d.getPincode(),d.getCountry(),d.getUserId(),d.getUserDetailsId());
                            addresses.add(address);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    textAddress.setText("You have "+addresses.size()+" addresses");
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetAddressResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    private void getUserDetails(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskRequest request = new GetTaskRequest(preference.getUserId());

        Call<GetUserDetails> call = client.getUserDetails(request);

        call.enqueue(new Callback<GetUserDetails>() {
            @Override
            public void onResponse(Call<GetUserDetails> call, Response<GetUserDetails> response) {
                if(response.body().getStatusCode().equals("0")){
                    ArrayList<GetUserData> userData = response.body().getData();
                    if(userData.size()>0) {
                        password = userData.get(0).getPassword().toString();
                        data = userData.get(0);
                        if(page.equals("Edit Profile")){
                            setData();
                        }
                    }
                }

                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetUserDetails> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    private void setData(){
        name.setText(data.getFullName().toString());
        mobile.setText(data.getMobile().toString());
        skills.setText(data.getKeySkills().toString());
        about.setText(data.getAbout().toString());
        if(data.getAccountType().equals("Personal")){
            personal.setChecked(true);
        }else if(data.getAccountType().equals("Business")){
            business.setChecked(true);
        }else if(data.getAccountType().equals("Both")){
            both.setChecked(true);
        }
    }

    private void updatePersonalDetails(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);
        RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        UpdateUserDetailsRequest request = new UpdateUserDetailsRequest(
                preference.getUserId(),
                radioButton.getText().toString(),
                skills.getText().toString(),
                about.getText().toString());


        Call<UploadFeedbackResponse> call = client.updateUserDetails(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                if(response.body().getStatusCode().equals("0")){

                }
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    private void updatePassword(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);
        ChangePassword request = new ChangePassword(
                preference.getUserId(),
                confirmPassword.getText().toString());


        Call<UploadFeedbackResponse> call = client.changePassword(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                if(response.body().getStatusCode().equals("0")){

                }
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }
}
