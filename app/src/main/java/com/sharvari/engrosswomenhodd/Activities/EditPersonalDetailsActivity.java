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
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RecyclerTouchListener;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;

import io.realm.RealmResults;

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
    private Users users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_details);

        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final String page = getIntent().getExtras().getString("Page");
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
                Intent i = new Intent(EditPersonalDetailsActivity.this, AddressAddActivity.class);
                i.putExtra("Type","Update");
                i.putExtra("Position",position+"");
                startActivity(i);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page.equals("Edit Profile")){
                    RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                    RealmController.with(EditPersonalDetailsActivity.this).updateProfileData(preference.getUserId(),
                    name.getText().toString(),
                    mobile.getText().toString(),
                    skills.getText().toString(),
                    about.getText().toString(),
                    radioButton.getText().toString());
                    preference.createLoginSession(name.getText().toString(),mobile.getText().toString(),preference.getUserId());
                }else if(page.equals("Change Password")){
                    if(oldPassword.getText().toString().isEmpty() && newPassword.getText().toString().isEmpty() && confirmPassword.getText().toString().isEmpty()){
                        Toast.makeText(EditPersonalDetailsActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        if(oldPassword.getText().toString().equals(users.getPassword())){
                            if(newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                                RealmController.with(EditPersonalDetailsActivity.this).updateProfilePassword(preference.getUserId(),newPassword.getText().toString().trim());
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

        selectedPage(page);
        setAddressData();
    }

    private void selectedPage(String page){
        users = RealmController.with(this).getCustomerDetails(preference.getUserId());
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

            name.setText(users.getFullName());
            mobile.setText(users.getMobile());
            skills.setText(users.getKeySkills());
            about.setText(users.getAbout());
            if(users.getAccountType().equals("Personal")){
                personal.setChecked(true);
            }else if(users.getAccountType().equals("Business")){
                business.setChecked(true);
            }else if(users.getAccountType().equals("Both")){
                both.setChecked(true);
            }

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

    private void setAddressData(){
        RealmResults<UserDetails> details = RealmController.with(this).getUserAddress(preference.getUserId());
        if(details.size()>0){
            for(UserDetails detail : details){
                Address address = new Address(detail.getAddressType(),detail.getAddress(),detail.getLandmark(),
                        detail.getArea(),detail.getCity(),detail.getPincode(),detail.getCountry());
                addresses.add(address);
            }
            adapter.notifyDataSetChanged();
        }

        textAddress.setText("You have "+addresses.size()+" addresses");
    }

}
