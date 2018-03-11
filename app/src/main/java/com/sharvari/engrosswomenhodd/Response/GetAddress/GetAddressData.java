package com.sharvari.engrosswomenhodd.Response.GetAddress;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetAddressData {


    String UserDetailsId;
    String UserId;
    String AddressType;
    String Address;
    String Landmark;
    String Area;
    String City;
    String Pincode;
    String Country;
    String CreatedOn;
    String UpdatedOn;


    public GetAddressData(String userDetailsId, String userId, String addressType,
                          String address, String landmark, String area, String city, String pincode,
                          String country, String createdOn, String updatedOn) {
        UserDetailsId = userDetailsId;
        UserId = userId;
        AddressType = addressType;
        Address = address;
        Landmark = landmark;
        Area = area;
        City = city;
        Pincode = pincode;
        Country = country;
        CreatedOn = createdOn;
        UpdatedOn = updatedOn;
    }

    public String getUserDetailsId() {
        return UserDetailsId;
    }

    public void setUserDetailsId(String userDetailsId) {
        UserDetailsId = userDetailsId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAddressType() {
        return AddressType;
    }

    public void setAddressType(String addressType) {
        AddressType = addressType;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLandmark() {
        return Landmark;
    }

    public void setLandmark(String landmark) {
        Landmark = landmark;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        UpdatedOn = updatedOn;
    }
}
