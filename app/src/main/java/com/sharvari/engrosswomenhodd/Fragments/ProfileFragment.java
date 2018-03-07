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
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class ProfileFragment extends Fragment{

    private ImageView imageView;
    private RatingBar ratingBar;
    private TextView name, totalTask, followers, following;
    private SharedPreference preference;
    private Users users;

    private CardView profile, password, address, invite;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        preference = new SharedPreference(getContext());

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

        users = RealmController.with(this).getCustomerDetails(preference.getUserId());

        setData();
        return v;
    }


    private void setData(){
        name.setText(users.getFullName());
        if(users.getPicture().equals("1")){
            Glide.with(this).load(R.drawable.img_teenager).apply(RequestOptions.circleCropTransform()).into(imageView);
        }else if(users.getPicture().equals("2")){
            Glide.with(this).load(R.drawable.img_women).apply(RequestOptions.circleCropTransform()).into(imageView);
        }else if(users.getPicture().equals("3")){
            Glide.with(this).load(R.drawable.img_old).apply(RequestOptions.circleCropTransform()).into(imageView);
        }

        followers.setText(RealmController.with(this).getProfileData(preference.getUserId(),"followers"));
        totalTask.setText(RealmController.with(this).getProfileData(preference.getUserId(),"totalTask"));
        following.setText(RealmController.with(this).getProfileData(preference.getUserId(),"following"));
    }

    public void openActivity(String page){

        Intent i = new Intent(getContext(), EditPersonalDetailsActivity.class);
        i.putExtra("Page",page);
        getContext().startActivity(i);
    }
}