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

import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Utils.RealmController;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class UploadNewsFragment extends Fragment{

    private EditText title, story, url;
    private Button upload;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upload_news, container, false);

        title = v.findViewById(R.id.title);
        story = v.findViewById(R.id.story);
        url = v.findViewById(R.id.url);
        context = getContext();

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
                    RealmController.with(UploadNewsFragment.this).uploadNews(title.getText().toString(),
                            story.getText().toString(),url.getText().toString());
                    title.setText("");
                    story.setText("");
                    url.setText("");
                    Toast.makeText(context, "Uploaded this news.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
