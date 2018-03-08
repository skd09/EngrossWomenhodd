package com.sharvari.engrosswomenhodd.Utils;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import io.realm.Realm;

/**
 * Created by sharvaridivekar on 07/03/18.
 */

public class Generic {

    public  static Generic instance;

    public Generic(Application application) {

    }

    public static Generic with(Fragment fragment) {

        if (instance == null) {
            instance = new Generic(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static Generic with(Activity activity) {

        if (instance == null) {
            instance = new Generic(activity.getApplication());
        }
        return instance;
    }

    public static Generic with(Application application) {

        if (instance == null) {
            instance = new Generic(application);
        }
        return instance;
    }

    public static Generic getInstance() {

        return instance;
    }



    public String getDaysLeft(long currentDate, long taskDate){
        long different = taskDate - currentDate;

        long diffInDays = different / (24 * 60 * 60 * 1000);

        return diffInDays+" days left";
    }

    public String dateFormat(Long date){
        Date d = new Date(date);
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(d);
    }

    public Long epochFormat(String date){
        return new Date(date).getTime();
    }

}
