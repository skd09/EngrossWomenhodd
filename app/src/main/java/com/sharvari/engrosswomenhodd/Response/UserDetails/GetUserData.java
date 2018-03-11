package com.sharvari.engrosswomenhodd.Response.UserDetails;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class GetUserData {


    String UserId;
    String FullName;
    String Mobile;
    String Password;
    String AccountType;
    String AgeGroup;
    String KeySkills;
    String About;
    String City;
    String Picture;
    String CreatedOn;
    String UpdatedOn;

    public GetUserData(String userId, String fullName, String mobile, String password, String accountType, String ageGroup, String keySkills, String about, String city, String picture, String createdOn, String updatedOn) {
        UserId = userId;
        FullName = fullName;
        Mobile = mobile;
        Password = password;
        AccountType = accountType;
        AgeGroup = ageGroup;
        KeySkills = keySkills;
        About = about;
        City = city;
        Picture = picture;
        CreatedOn = createdOn;
        UpdatedOn = updatedOn;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getAgeGroup() {
        return AgeGroup;
    }

    public void setAgeGroup(String ageGroup) {
        AgeGroup = ageGroup;
    }

    public String getKeySkills() {
        return KeySkills;
    }

    public void setKeySkills(String keySkills) {
        KeySkills = keySkills;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
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
