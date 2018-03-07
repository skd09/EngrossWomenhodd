package com.sharvari.engrosswomenhodd.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.RealmController;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class ProfileActivity extends AppCompatActivity {

    private ImageView imageView;
    private RatingBar ratingBar;
    private TextView name, about, location, taskDone, followers, following;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        String userId = getIntent().getExtras().getString("UserId");
        String userLocation = getIntent().getExtras().getString("Location");

        Users users = RealmController.with(this).getCustomerDetails(userId);

        imageView = findViewById(R.id.picture);
        name = findViewById(R.id.name);
        about = findViewById(R.id.about);
        location = findViewById(R.id.location);
        taskDone = findViewById(R.id.taskDone);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar1);


        name.setText(users.getFullName());
        about.setText(users.getAbout());
        location.setText(userLocation);

        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Glide.with(this).load(R.drawable.img_women).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
}
