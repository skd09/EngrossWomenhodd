package com.sharvari.engrosswomenhodd.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharvari.engrosswomenhodd.Pojos.home;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Follow;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.FollowRequest;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Response.GetProfileDetailsResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Response.UserDetails.GetUserData;
import com.sharvari.engrosswomenhodd.Response.UserDetails.GetUserDetails;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class ProfileActivity extends AppCompatActivity {

    private ImageView imageView;
    private RatingBar ratingBar;
    private TextView name, about, location, taskDone, followers, following;
    private String userId;
    private home user;
    private FloatingActionButton fab;
    private SharedPreference preference;
    private Context context;
    private Loader loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        context = this;
        preference = new SharedPreference(this);
        loader = new Loader(this);

        if(getIntent().getExtras().getString("Page").equals("Home")){
            Bundle data = getIntent().getExtras();
            user = (home) data.getParcelable("home");
            getUserDetails(user.getUserId());
        }else{
            getUserDetails(getIntent().getExtras().getString("UserId"));
        }

        imageView = findViewById(R.id.picture);
        name = findViewById(R.id.name);
        about = findViewById(R.id.about);
        location = findViewById(R.id.location);
        taskDone = findViewById(R.id.taskDone);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar1);
        fab=findViewById(R.id.fab);





        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getExtras().getString("UserId").equals("Home")){
                    followUser(user.getUserId());
                }else{
                    followUser(getIntent().getExtras().getString("UserId"));
                }

            }
        });

    }


    private void getUserDetails(final String userId){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskRequest request = new GetTaskRequest(userId);

        Call<GetUserDetails> call = client.getUserDetails(request);

        call.enqueue(new Callback<GetUserDetails>() {
            @Override
            public void onResponse(Call<GetUserDetails> call, Response<GetUserDetails> response) {
                if(response.body().getStatusCode().equals("0")){
                    ArrayList<GetUserData> data = response.body().getData();
                    if(data.size()>0) {
                        GetUserData users = data.get(0);
                        name.setText(users.getFullName());
                        about.setText(users.getAbout());
                        if(getIntent().getExtras().getString("Page").equals("Home")) {
                            location.setText(getIntent().getExtras().getString("Location"));
                        }else{
                            location.setText(users.getCity());
                        }
                        if(users.getPicture().equals("1")){
                            Glide.with(ProfileActivity.this).load(R.drawable.img_teenager).apply(RequestOptions.circleCropTransform()).into(imageView);
                        }else if(users.getPicture().equals("2")){
                            Glide.with(ProfileActivity.this).load(R.drawable.img_women).apply(RequestOptions.circleCropTransform()).into(imageView);
                        }else if(users.getPicture().equals("3")){
                            Glide.with(ProfileActivity.this).load(R.drawable.img_old).apply(RequestOptions.circleCropTransform()).into(imageView);
                        }

                    }
                }
                geProfileDetails(userId);
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetUserDetails> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }


    private void geProfileDetails(String userId){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskRequest request = new GetTaskRequest(userId);

        Call<GetProfileDetailsResponse> call = client.getProfileDetails(request);

        call.enqueue(new Callback<GetProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<GetProfileDetailsResponse> call, Response<GetProfileDetailsResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    taskDone.setText(response.body().getTaskDone());
                    followers.setText(response.body().getFollowersCount());
                    following.setText(response.body().getFollowingCount());
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetProfileDetailsResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }



    private void followUser(String userId){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        FollowRequest request = new FollowRequest(preference.getUserId(), userId);

        Call<UploadFeedbackResponse> call = client.followRequest(request);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                loader.hideLoader();
                if(response.body().getStatusCode().equals("0")){
                    Toast.makeText(ProfileActivity.this, "Your are now following "+name.getText(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ProfileActivity.this, response.body().getStatusMsg()+" "+name.getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

}
