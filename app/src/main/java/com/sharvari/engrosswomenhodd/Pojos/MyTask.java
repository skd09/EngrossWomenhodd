package com.sharvari.engrosswomenhodd.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class MyTask implements Parcelable {

    String id, name, date, description,status, location, amount, requestCount, title, addressId;

    public MyTask(String id, String name, String date, String description, String status, String location, String amount, String requestCount, String title, String addressId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.status = status;
        this.location = location;
        this.amount = amount;
        this.requestCount = requestCount;
        this.title = title;
        this.addressId = addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(String requestCount) {
        this.requestCount = requestCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public MyTask(Parcel in){
        String[] data = new String[10];

        in.readStringArray(data);
        this.id = data[0];
        this.name = data[1];
        this.date = data[2];
        this.description = data[3];
        this.status = data[4];
        this.amount = data[5];
        this.location = data[6];
        this.requestCount = data[7];
        this.title = data[8];
        this.addressId = data[9];
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                this.id = id,
                this.name = name,
                this.date = date,
                this.description = description,
                this.status = status,
                this.amount = amount,
                this.location = location,
                this.requestCount = requestCount,
                this.title = title,
                this.addressId = addressId
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MyTask createFromParcel(Parcel in) {
            return new MyTask(in);
        }

        public MyTask[] newArray(int size) {
            return new MyTask[size];
        }
    };
}
