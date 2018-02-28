package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharvari.engrosswomenhodd.R;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class ProfileFragment extends Fragment{

    private ImageView imageView;
    private RatingBar ratingBar;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        imageView = v.findViewById(R.id.picture);
        ratingBar=(RatingBar)v.findViewById(R.id.ratingBar1);

        Glide.with(this).load(R.drawable.img_women).apply(RequestOptions.circleCropTransform()).into(imageView);


        return v;
    }

}