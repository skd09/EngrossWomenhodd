package com.sharvari.engrosswomenhodd.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by sharvari on 6/8/2017.
 */

public class Loader {

    private Context context;
    private ProgressDialog progressDialog ;

    public Loader(Context c){
        context = c;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);
    }
    public void setTitle(String message){
        progressDialog.setMessage(message);
    }
    public void showLoader(){
        progressDialog.show();
    }
    public void hideLoader(){
        progressDialog.dismiss();
    }


}
