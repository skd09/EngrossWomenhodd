package com.sharvari.engrosswomenhodd.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sharvaridivekar on 02/03/18.
 */

public class Address implements Parcelable{

    String type, line1, landmark,area,city, pincode,country, userId, taskId;

    public Address() {
    }

    public Address(String type, String line1, String landmark, String area, String city, String pincode, String country) {
        this.type = type;
        this.line1 = line1;
        this.landmark = landmark;
        this.area = area;
        this.city = city;
        this.pincode = pincode;
        this.country = country;
    }

    public Address(String type, String line1, String landmark, String area, String city, String pincode, String country, String userId, String taskId) {
        this.type = type;
        this.line1 = line1;
        this.landmark = landmark;
        this.area = area;
        this.city = city;
        this.pincode = pincode;
        this.country = country;
        this.userId = userId;
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address(Parcel in){
        String[] data = new String[9];

        in.readStringArray(data);
        this.type = data[0];
        this.line1 = data[1];
        this.landmark = data[2];
        this.area = data[3];
        this.city = data[4];
        this.pincode = data[5];
        this.country = data[6];
        this.userId = data[7];
        this.taskId = data[8];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                this.type = type,
                this.line1 = line1,
                this.landmark = landmark,
                this.area = area,
                this.city = city,
                this.pincode = pincode,
                this.country = country,
                this.userId = userId,
                this.taskId = taskId
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
