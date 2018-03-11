package com.sharvari.engrosswomenhodd.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

/**
 * Created by sharvaridivekar on 12/03/18.
 */

public class SplashScreenActivity extends AppCompatActivity{

    private SharedPreference preference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preference = new SharedPreference(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        try {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    if(preference.isLoggedIn().equals("0")){
                        Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        return;
                    }else {
                        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.putExtra("Mode","Login");
                        startActivity(i);
                    }
                }


            }, 5000);

        } catch (Exception e) {

        }



    }
}
