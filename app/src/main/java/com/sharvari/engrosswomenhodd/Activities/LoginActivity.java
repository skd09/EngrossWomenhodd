package com.sharvari.engrosswomenhodd.Activities;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Requests.UserLoginRequest;
import com.sharvari.engrosswomenhodd.Requests.UserRegisterRequest;
import com.sharvari.engrosswomenhodd.Response.UserLoginResponse;
import com.sharvari.engrosswomenhodd.Response.UserRegisterResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.AppData;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.UUID;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvari on 09-Feb-18.
 */

public class LoginActivity extends AppCompatActivity {


    public enum Mode{
        Register,
        Login
    }

    private Realm realm;

    private EditText mobile, password, name, city;
    private ImageView teen, mid, old;
    private RadioGroup radio;
    private Button login, register;
    private LinearLayout layout;
    private String picture = "";
    private Loader loader;

    private SharedPreference preference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        realm = Realm.getDefaultInstance();
        loader = new Loader(this);
        preference = new SharedPreference(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        setData();

        layout = findViewById(R.id.linear);

        mobile = findViewById(R.id.et_mobile);
        password = findViewById(R.id.et_password);
        name = findViewById(R.id.et_full_name);
        city = findViewById(R.id.et_city);

//        name.setText("Sharvari Divekar");
//        mobile.setText("9999900000");
//        password.setText("skd");

        teen = findViewById(R.id.teen);
        mid = findViewById(R.id.mid);
        old = findViewById(R.id.old);

        radio = findViewById(R.id.radio);

        register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.btnLogin);

        teen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               picture = "1";
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picture = "2";
            }
        });
        old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picture = "3";
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields(Mode.Register);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields(Mode.Login);
            }
        });
        Toast.makeText(this, "All fields are mandatory.", Toast.LENGTH_LONG).show();

    }

    private void validateFields(Mode mode){

        if(name.getText().toString().isEmpty()){
            Snackbar.make(layout,"Enter your name.",Snackbar.LENGTH_LONG).show();
            return;
        }else if(mobile.getText().toString().isEmpty()){
            Snackbar.make(layout,"Enter your mobile number.",Snackbar.LENGTH_LONG).show();
            return;
        }else if(password.getText().toString().isEmpty()){
            Snackbar.make(layout,"Enter password.",Snackbar.LENGTH_LONG).show();
            return;
        }else if(city.getText().toString().isEmpty() && mode.equals(Mode.Register)){
            Snackbar.make(layout,"Enter your city.",Snackbar.LENGTH_LONG).show();
            return;
        }else if(picture.equals("") && mode.equals(Mode.Register)){
            Snackbar.make(layout,"Select one picture.",Snackbar.LENGTH_LONG).show();
            return;
        }

        if(mode.equals(Mode.Login)){
            onLoginSelect();
        }else {
            onRegisterSelect();
        }
    }

    private void onLoginSelect(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);
        UserLoginRequest registerRequest = new UserLoginRequest(
                name.getText().toString().trim(),
                password.getText().toString().trim(),
                mobile.getText().toString().trim()
        );
        Call<UserLoginResponse> call = client.userLogin(registerRequest);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse>call, Response<UserLoginResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    preference.createLoginSession(name.getText().toString(),mobile.getText().toString(),
                            response.body().getUserId(),"","");
                    callIntent("Login");
                }else{
                    Toast.makeText(LoginActivity.this, response.body().getStatusMsg(), Toast.LENGTH_SHORT).show();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<UserLoginResponse>call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    public void onRegisterSelect()
    {
        loader.showLoader();
        final RadioButton radioButton = (RadioButton) findViewById(radio.getCheckedRadioButtonId());
        Apis client = RetrofitClient.getClient().create(Apis.class);
        UserRegisterRequest registerRequest = new UserRegisterRequest(
                name.getText().toString().trim(),
                password.getText().toString().trim(),
                mobile.getText().toString().trim(),
                city.getText().toString().trim(),
                picture,
                radioButton.getText().toString()
        );
        Call<UserRegisterResponse> call = client.registerUser(registerRequest);
        call.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse>call, Response<UserRegisterResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    preference.createLoginSession(name.getText().toString().trim(),
                            mobile.getText().toString().trim(),
                            response.body().getUserId(),picture,radioButton.getText().toString());
                    callIntent("Register");
                }else{
                    Toast.makeText(LoginActivity.this, response.body().getStatusMsg(), Toast.LENGTH_SHORT).show();
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<UserRegisterResponse>call, Throwable t) {
                loader.hideLoader();
            }
        });
    }

    private void setData(){
        AppData data = new AppData();
        data.setData(this);
    }

    private void callIntent(String mode){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("Mode",mode);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
