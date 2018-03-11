package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class UpdateAddressRequest {

    String id;
    String userId;
    String accountType;
    String address;
    String landmark;
    String area;
    String city;
    String pincode;
    String country;

    public UpdateAddressRequest(String id, String userId, String accountType, String address, String landmark, String area, String city, String pincode, String country) {
        this.id = id;
        this.userId = userId;
        this.accountType = accountType;
        this.address = address;
        this.landmark = landmark;
        this.area = area;
        this.city = city;
        this.pincode = pincode;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
