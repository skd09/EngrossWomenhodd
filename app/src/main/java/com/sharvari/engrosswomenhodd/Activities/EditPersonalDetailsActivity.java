package com.sharvari.engrosswomenhodd.Activities;

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
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.util.ArrayList;

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
    private SharedPreference preference;

    private RadioGroup radioGroup;
    private RadioButton personal, business, both;
    private Button update;

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
                Address address = new Address();
                addresses.add(address);

                adapter.notifyDataSetChanged();
                textAddress.setText("You have "+addresses.size()+" addresses");
                Toast.makeText(getApplicationContext(), "New address inserted. Don't forget to update it.", Toast.LENGTH_LONG).show();
            }
        });
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

                }else if(page.equals("Update Address")){


                }else if(page.equals("Invite a Friend")){

                }
                Toast.makeText(EditPersonalDetailsActivity.this, "Details updated.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        selectedPage(page);
        setAddressData();
    }

    private void selectedPage(String page){
        Users users = RealmController.with(this).getCustomerDetails(preference.getUserId());
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
        }else if(page.equals("Update Address")){
            layout_address.setVisibility(View.VISIBLE);
            layout_password.setVisibility(View.GONE);
            layout_invite.setVisibility(View.GONE);
            layout_profile.setVisibility(View.GONE);
            Toast.makeText(this, "Swipe right to see you address.", Toast.LENGTH_LONG).show();
        }else if(page.equals("Invite a Friend")){
            layout_address.setVisibility(View.GONE);
            layout_password.setVisibility(View.GONE);
            layout_invite.setVisibility(View.VISIBLE);
            layout_profile.setVisibility(View.GONE);
        }
    }

    private void setAddressData(){
        Address address = new Address();
        addresses.add(address);

        address = new Address();
        addresses.add(address);

        adapter.notifyDataSetChanged();
        textAddress.setText("You have "+addresses.size()+" addresses");
    }

}
