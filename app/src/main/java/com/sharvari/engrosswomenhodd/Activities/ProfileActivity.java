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
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Follow;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.UUID;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class ProfileActivity extends AppCompatActivity {

    private ImageView imageView;
    private RatingBar ratingBar;
    private TextView name, about, location, taskDone, followers, following;
    private String userId;
    private Users users;
    private FloatingActionButton fab;
    private SharedPreference preference;
    private Context context;

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

        userId = getIntent().getExtras().getString("UserId");
        String userLocation = getIntent().getExtras().getString("Location");

        users = RealmController.with(this).getCustomerDetails(userId);

        imageView = findViewById(R.id.picture);
        name = findViewById(R.id.name);
        about = findViewById(R.id.about);
        location = findViewById(R.id.location);
        taskDone = findViewById(R.id.taskDone);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar1);
        fab=findViewById(R.id.fab);


        name.setText(users.getFullName());
        about.setText(users.getAbout());
        location.setText(userLocation);



        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Follow f = new Follow(
                        UUID.randomUUID().toString(),
                        userId,
                        preference.getUserId(),
                        "0",
                        System.currentTimeMillis()
                );
                int code = RealmController.with(ProfileActivity.this).insertFollow(f);
                if(code == 1)
                    Toast.makeText(context, "Follow request sent to "+users.getFullName(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "You already sent request to "+users.getFullName(), Toast.LENGTH_SHORT).show();
            }
        });

        setData();
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

        followers.setText(RealmController.with(this).getProfileData(userId,"followers"));
        following.setText(RealmController.with(this).getProfileData(userId,"totalTask"));
        taskDone.setText(RealmController.with(this).getProfileData(userId,"totalTask"));
    }
}
