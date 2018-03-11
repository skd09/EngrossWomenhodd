package com.sharvari.engrosswomenhodd.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.sharvari.engrosswomenhodd.Adapters.BusinessAdapter;
import com.sharvari.engrosswomenhodd.Pojos.Business;
import com.sharvari.engrosswomenhodd.R;
import com.sharvari.engrosswomenhodd.Requests.UploadBusinessRequest;
import com.sharvari.engrosswomenhodd.Response.GetBusiness.GetBusinessData;
import com.sharvari.engrosswomenhodd.Response.GetBusiness.GetBusinessResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Services.Apis;
import com.sharvari.engrosswomenhodd.Utils.Loader;
import com.sharvari.engrosswomenhodd.Utils.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class BusinessFragment extends Fragment {

    private BusinessAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Business> arrayList = new ArrayList<>();
    private Loader loader;
    private SwipeRefreshLayout swiperefresh;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_business, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new BusinessAdapter(arrayList, getContext());
        loader = new Loader(getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        onBusinessUploadClick ();

        swiperefresh = v.findViewById(R.id.swiperefresh);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onBusinessUploadClick();
                swiperefresh.setRefreshing(false);
            }
        });

        adapter.OnMoreClickListener(new BusinessAdapter.SetMoreClickListener() {
            @Override
            public void OnMoreClick(View v, int position) {
                showChangeLangDialog();
            }
        });

        return v;
    }



    private void onBusinessUploadClick(){
        arrayList.clear();
        loader.showLoader();
        Apis client = RetrofitClient.getClient().create(Apis.class);

        Call<GetBusinessResponse> call = client.getBusiness();

        call.enqueue(new Callback<GetBusinessResponse>() {
            @Override
            public void onResponse(Call<GetBusinessResponse> call, Response<GetBusinessResponse> response) {
                if(response.body().getStatusCode().equals("0")){
                    if(response.body().getData().size()>0){
                        for(GetBusinessData data: response.body().getData()){
                            Business n = new Business(
                                    data.getBusinessId(),
                                    data.getCompanyName(),
                                    data.getDescription(),
                                    data.getFullName(),
                                    data.getURL(),
                                    dateFormat(data.getCreatedOn()),
                                    data.getUserId()
                                );
                            arrayList.add(n);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                loader.hideLoader();
            }

            @Override
            public void onFailure(Call<GetBusinessResponse> call, Throwable t) {
                loader.hideLoader();
            }
        });

    }


    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.popup_request, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.charges);
        edt.setVisibility(View.GONE);
        final EditText edt1 = (EditText) dialogView.findViewById(R.id.why);
        edt1.setHint("What you want?");

        dialogBuilder.setTitle("Do you want to request?");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "This user will contact you directly.\nThank you.", Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private String dateFormat(String dateStr){

        String[] format = dateStr.split(" ")[0].split("-");

        String formatedDate = format[2] + "/" + format[1] + "/" + format[0];
        return formatedDate;
    }

}