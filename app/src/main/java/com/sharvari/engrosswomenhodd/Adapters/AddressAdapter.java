package com.sharvari.engrosswomenhodd.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sharvari.engrosswomenhodd.Pojos.Address;
import com.sharvari.engrosswomenhodd.R;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 02/03/18.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder>{

    private ArrayList<Address> arrayList = new ArrayList<>();

    public AddressAdapter(ArrayList<Address> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_personal_details, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Address address = arrayList.get(position);

        holder.addressNumber.setText("Address "+(position+1));

        holder.type.setText(address.getType());
        holder.type.setFocusable(false);

        holder.address.setText(address.getLine1());
        holder.address.setFocusable(false);

        holder.landmark.setText(address.getLandmark());
        holder.landmark.setFocusable(false);

        holder.area.setText(address.getArea());
        holder.area.setFocusable(false);

        holder.city.setText(address.getCity());
        holder.city.setFocusable(false);

        holder.pincode.setText(address.getPincode());
        holder.pincode.setFocusable(false);

        holder.country.setText(address.getCountry());
        holder.country.setFocusable(false);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText type, address, landmark, area, city, pincode, country;
        TextView addressNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            address = itemView.findViewById(R.id.address);
            landmark = itemView.findViewById(R.id.landmark);
            area = itemView.findViewById(R.id.area);
            city = itemView.findViewById(R.id.city);
            pincode = itemView.findViewById(R.id.pincode);
            country = itemView.findViewById(R.id.country);
            addressNumber = itemView.findViewById(R.id.addressNumber);
        }
    }

}
