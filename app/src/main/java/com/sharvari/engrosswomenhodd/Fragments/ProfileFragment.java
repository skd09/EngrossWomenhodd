package com.sharvari.engrosswomenhodd.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharvari.engrosswomenhodd.Activities.EditPersonalDetailsActivity;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.FollowRequest;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Response.GetProfileDetailsResponse;
import com.sharvari.engrosswomenhodd.Response.UserDetails.GetUserData;
import com.sharvari.engrosswomenhodd.Response.UserDetails.GetUserDetails;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class ProfileFragment extends Fragment{

    private ImageView imageView;
    private RatingBar ratingBar;
    private TextView name, totalTask, followers, following;
    private SharedPreference preference;
    private Loader loader;

    private CardView profile, password, address, invite;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        preference = new SharedPreference(getContext());
        loader = new Loader(getContext());

        imageView = v.findViewById(R.id.picture);
        ratingBar=(RatingBar)v.findViewById(R.id.ratingBar1);



        profile = v.findViewById(R.id.layout_profile);
        password = v.findViewById(R.id.layout_password);
        address = v.findViewById(R.id.layout_address);
        invite = v.findViewById(R.id.layout_invite);

        name = v.findViewById(R.id.name);
        totalTask = v.findViewById(R.id.totalTask);
        followers = v.findViewById(R.id.followers);
        following = v.findViewById(R.id.following);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Edit Profile");
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Change Password");
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Update Address");
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Invite a Friend");
            }
        });


        if(preference.getUserImage().equals("")){
            getUserDetails();
        }else{
            setData();
        }
        geProfileDetails();
        return v;
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
                    ArrayList<GetUserData> data = response.body().getData();
                    if(data.size()>0) {

                        preference.createLoginSession(
                                data.get(0).getFullName().toString(),
                                data.get(0).getMobile().toString(),
                                data.get(0).getUserId().toString(),
                                data.get(0).getPicture().toString(),
                                data.get(0).getAccountType().toString());

                    }
                    setData();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetUserDetails> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    private void geProfileDetails(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        GetTaskRequest request = new GetTaskRequest(preference.getUserId());

        Call<GetProfileDetailsResponse> call = client.getProfileDetails(request);

        call.enqueue(new Callback<GetProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<GetProfileDetailsResponse> call, Response<GetProfileDetailsResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    totalTask.setText(response.body().getTaskDone());
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

    private void setData(){
        name.setText(preference.getUserName());

        if(preference.getUserImage().equals("1")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.img_teenager));
        }else if(preference.getUserImage().equals("2")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.img_women));
        }else if(preference.getUserImage().equals("3")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.img_old));
        }


        followers.setText("0");
        totalTask.setText("0");
        following.setText("0");
    }

    public void openActivity(String page){

        Intent i = new Intent(getContext(), EditPersonalDetailsActivity.class);
        i.putExtra("Page",page);
        getContext().startActivity(i);
    }
}