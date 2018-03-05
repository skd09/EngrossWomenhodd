package com.sharvari.engrosswomenhodd.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class UploadTaskFragment extends Fragment {

    Spinner typeSpinner;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upload_task, container, false);

        typeSpinner = v.findViewById(R.id.typeSpinner);
        ArrayList<String> array = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.layout_spinner,
                array
        );
        typeSpinner.setAdapter(adapter);

        return v;
    }
}