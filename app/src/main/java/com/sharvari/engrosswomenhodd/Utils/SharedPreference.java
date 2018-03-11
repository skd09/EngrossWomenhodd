package com.sharvari.engrosswomenhodd.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.sharvari.engrosswomenhodd.Activities.LoginActivity;

import java.util.HashMap;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class SharedPreference {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Sharvari";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";

    public static final String KEY_MOBILE = "mobile";

    public static final String KEY_USER_ID = "userId";

    public static final String KEY_USER_IMG = "img";

    public static final String KEY_USER_Type = "userType";


    public SharedPreference(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String mobile, String userId,String img, String type){
        editor.putString(IS_LOGIN, "1");

        editor.putString(KEY_NAME, name);

        editor.putString(KEY_MOBILE, mobile);

        editor.putString(KEY_USER_ID, userId);

        editor.putString(KEY_USER_IMG, img);

        editor.putString(KEY_USER_Type, type);

        editor.commit();
    }


    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        _context.startActivity(i);
    }


    public String isLoggedIn(){
        return pref.getString(IS_LOGIN, "0");
    }

    public String getUserName(){
        return pref.getString(KEY_NAME, "");
    }

    public String getUserMobile(){
        return pref.getString(KEY_MOBILE, "");
    }

    public String getUserImage(){
        return pref.getString(KEY_USER_IMG, "");
    }
    public String getUserId(){
        return pref.getString(KEY_USER_ID, "");
    }
    public String getUserType(){
        return pref.getString(KEY_USER_Type, "");
    }
}
