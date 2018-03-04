package com.sharvari.engrosswomenhodd.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Fragments.BusinessFragment;
import com.sharvari.engrosswomenhodd.Fragments.FeedbackFragment;
import com.sharvari.engrosswomenhodd.Fragments.HomeFragment;
import com.sharvari.engrosswomenhodd.Fragments.MyTaskFragment;
import com.sharvari.engrosswomenhodd.Fragments.NewsFragment;
import com.sharvari.engrosswomenhodd.Fragments.ProfileFragment;
import com.sharvari.engrosswomenhodd.Fragments.SeeFeedbackFragment;
import com.sharvari.engrosswomenhodd.Fragments.UploadNewsFragment;
import com.sharvari.engrosswomenhodd.Fragments.UploadTaskFragment;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Realm.Users;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private SharedPreference preference;
    private NavigationView navigationView;
    private Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        preference = new SharedPreference(this);

        setSupportActionBar(toolbar);

        if(preference.isLoggedIn().equals("0")){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return;
        }else {
            String ToastMessage = "";

            if (getIntent().getExtras() != null && getIntent().getExtras().getString("Mode")!=null) {
                if (getIntent().getExtras().getString("Mode").equals("Login")){
                    ToastMessage = "Welcome back " + preference.getUserName();
                } else if (getIntent().getExtras().getString("Mode").equals("Register")) {
                    ToastMessage = "Warn welcome " + preference.getUserName() + ".\nThanks for joining us.";
                }
                Toast.makeText(this, ToastMessage, Toast.LENGTH_SHORT).show();
            }
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        showFragment(new HomeFragment(),"Home");

        setData();


    }

    private void setData(){
        user = RealmController.with(this).getCustomerDetails(preference.getUserId());

        View headerLayout = navigationView.getHeaderView(0);

        TextView name = headerLayout.findViewById(R.id.login_name);
        TextView mobile = headerLayout.findViewById(R.id.login_mobile);
        ImageView imageView = headerLayout.findViewById(R.id.login_picture);

        name.setText(user.getFullName());
        mobile.setText(user.getCity());
        if(user.getPicture().equals("1")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.img_teenager));
        }else if(user.getPicture().equals("2")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.img_women));
        }else if(user.getPicture().equals("3")){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.img_old));
        }

        if(!user.getAccountType().equals("Admin")){
            navigationView.getMenu().findItem(R.id.nav_upload_news).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_see_feedback).setVisible(false);
        }else{
            navigationView.getMenu().findItem(R.id.nav_feedback).setVisible(false);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback) {
            showFragment(new FeedbackFragment(),"Feedback");
        } else if (id == R.id.nav_home) {
            showFragment(new HomeFragment(),"Home");
        } else if (id == R.id.nav_news) {
            showFragment(new NewsFragment()," News");
        } else if (id == R.id.nav_profile) {
            showFragment(new ProfileFragment(),"Profile");
        } else if (id == R.id.nav_business) {
            showFragment(new BusinessFragment(),"Business");
        } else if (id == R.id.nav_my_task) {
            showFragment(new MyTaskFragment(),"My Task");
        }else if (id == R.id.nav_task_upload) {
            showFragment(new UploadTaskFragment(),"Upload Task");
        }else if (id == R.id.nav_task_upload) {
            showFragment(new UploadTaskFragment(),"Upload Task");
        }else if (id == R.id.nav_logout) {
            onLogoutClick();
        }else if (id == R.id.nav_upload_news) {
            showFragment(new UploadNewsFragment(),"Upload News");
        }else if (id == R.id.nav_see_feedback) {
            showFragment(new SeeFeedbackFragment(),"Users Feedback");
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(Fragment fragment, String title){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
        toolbar.setTitle(title);
    }

    private void onLogoutClick(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Logout");
        alert.setMessage("Are you sure you want to logout?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                preference.logoutUser();
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }
}
