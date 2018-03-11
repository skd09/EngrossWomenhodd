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
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Pojos.SeeFeedback;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Requests.UploadNewsRequest;
import com.sharvari.engrosswomenhodd.Response.Feedback.GetFeedbackList;
import com.sharvari.engrosswomenhodd.Response.Feedback.GetFeedbackResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RealmController;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class UploadNewsFragment extends Fragment{

    private EditText title, story, url;
    private Button upload;
    private Context context;
    private Loader loader;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upload_news, container, false);

        title = v.findViewById(R.id.title);
        story = v.findViewById(R.id.story);
        url = v.findViewById(R.id.url);
        context = getContext();

        loader = new Loader(getContext());

        upload = v.findViewById(R.id.upload);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = title.getText().toString();
                String s = story.getText().toString();
                String u = url.getText().toString();
                if(t.isEmpty() && s.isEmpty() && u.isEmpty()){
                    Toast.makeText(context, "All fields are mandatory.", Toast.LENGTH_SHORT).show();
                }else{
                    OnUploadButton();
                }
            }
        });

        return v;
    }

    private void OnUploadButton(){
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);
        UploadNewsRequest news = new UploadNewsRequest(
                title.getText().toString(),
                story.getText().toString(),
                url.getText().toString()
        );
        Call<UploadFeedbackResponse> call = client.uploadNews(news);

        call.enqueue(new Callback<UploadFeedbackResponse>() {
            @Override
            public void onResponse(Call<UploadFeedbackResponse> call, Response<UploadFeedbackResponse> response) {
                title.setText("");
                story.setText("");
                url.setText("");
                Toast.makeText(context, "Uploaded this news.", Toast.LENGTH_SHORT).show();
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<UploadFeedbackResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });
    }
}
