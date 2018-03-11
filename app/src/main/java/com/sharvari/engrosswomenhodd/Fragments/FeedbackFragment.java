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
import com.sharvari.engrosswomenhodd.Requests.UploadFeedbackRequest;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;
import com.sharvari.engrosswomenhodd.Utils.SharedPreference;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvaridivekar on 27/02/18.
 */

public class FeedbackFragment extends Fragment {

    private EditText desc;
    private RatingBar ratingBar;
    private Button submit;
    private SharedPreference preference;
    private Context context;
    private Loader loader;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        desc = v.findViewById(R.id.et_feedback);
        submit = v.findViewById(R.id.btnSubmit);
        preference = new SharedPreference(getContext());
        context = getContext();
        loader = new Loader(context);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick();
            }
        });
        return v;
    }

    private void onButtonClick(){

        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);
        UploadFeedbackRequest registerRequest = new UploadFeedbackRequest(
                preference.getUserId(),
                desc.getText().toString()
        );
        Call<UploadFeedbackResponse> call = client.uploadFeedback(registerRequest);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                Toast.makeText(context, "We appreciate your feedback.", Toast.LENGTH_SHORT).show();
                desc.setText("");
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });

    }

}