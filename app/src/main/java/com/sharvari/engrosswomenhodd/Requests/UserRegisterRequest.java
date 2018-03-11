package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class UserRegisterRequest {

    String username;
    String password;
    String mobile;
    String city;
    String picture;
    String userType;

    public UserRegisterRequest(String username, String password, String mobile, String city, String picture, String userType) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.city = city;
        this.picture = picture;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
