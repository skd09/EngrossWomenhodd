package com.sharvari.engrosswomenhodd.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.text.DecimalFormat;

/**
 * Created by sharvaridivekar on 27/02/18.
 */

public class FeedbackFragment extends Fragment {

    private EditText desc;
    private RatingBar ratingBar;
    private Button submit;
    private SharedPreference preference;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        desc = v.findViewById(R.id.et_feedback);
        submit = v.findViewById(R.id.btnSubmit);
        preference = new SharedPreference(getContext());
        context = getContext();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(desc.getText().toString().isEmpty()){
                    Toast.makeText(context, "Enter your feedback.", Toast.LENGTH_LONG).show();
                }else{
                    RealmController.with(FeedbackFragment.this).shareFeedback(desc.getText().toString(),preference.getUserId());
                    Toast.makeText(context, "We appreciate your feedback.", Toast.LENGTH_SHORT).show();
                    desc.setText("");
                }
            }
        });
        return v;
    }


}