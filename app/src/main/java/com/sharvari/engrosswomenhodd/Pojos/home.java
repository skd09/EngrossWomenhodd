package com.sharvari.engrosswomenhodd.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class home implements Parcelable {
    String id, picture, name, leftDays, request, title, description, amount, location, userId;

    public home(String id, String picture, String name, String leftDays, String request, String title, String description, String amount, String location, String userId) {
        this.id = id;
        this.picture = picture;
        this.name = name;
        this.leftDays = leftDays;
        this.request = request;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.location = location;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(String leftDays) {
        this.leftDays = leftDays;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public home(Parcel in){
        String[] data = new String[10];

        in.readStringArray(data);
        this.id = data[0];
        this.picture = data[1];
        this.name = data[2];
        this.leftDays = data[3];
        this.request = data[4];
        this.title = data[5];
        this.description = data[6];
        this.amount = data[7];
        this.location = data[8];
        this.userId = data[9];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
            this.id = id,
            this.picture = picture,
            this.name = name,
            this.leftDays = leftDays,
            this.request = request,
            this.title = title,
            this.description = description,
            this.amount = amount,
            this.location = location,
            this.userId = userId
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public home createFromParcel(Parcel in) {
            return new home(in);
        }

        public home[] newArray(int size) {
            return new home[size];
        }
    };
}
