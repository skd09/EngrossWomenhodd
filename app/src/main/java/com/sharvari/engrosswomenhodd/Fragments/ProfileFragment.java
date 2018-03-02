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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharvari.engrosswomenhodd.Activities.EditPersonalDetailsActivity;
import com.sharvari.engrosswomenhodd.R;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class ProfileFragment extends Fragment{

    private ImageView imageView;
    private RatingBar ratingBar;

    private CardView profile, password, address, invite;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        imageView = v.findViewById(R.id.picture);
        ratingBar=(RatingBar)v.findViewById(R.id.ratingBar1);

        Glide.with(this).load(R.drawable.img_women).apply(RequestOptions.circleCropTransform()).into(imageView);

        profile = v.findViewById(R.id.layout_profile);
        password = v.findViewById(R.id.layout_password);
        address = v.findViewById(R.id.layout_address);
        invite = v.findViewById(R.id.layout_invite);

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
        return v;
    }


    public void openActivity(String page){

        Intent i = new Intent(getContext(), EditPersonalDetailsActivity.class);
        i.putExtra("Page",page);
        getContext().startActivity(i);
    }
}