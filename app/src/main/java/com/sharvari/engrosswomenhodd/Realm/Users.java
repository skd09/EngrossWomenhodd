package com.sharvari.engrosswomenhodd.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class Users extends RealmObject{

    @PrimaryKey
    String UserId;
    String FullName;
    String Mobile;
    String Password;
    String AccountType;
    String AgeGroup;
    String KeySkills;
    String About;
    String Picture;
    String City;
    Long CreatedOn;
    Long UpdatedOn;

    public Users() {
    }

    public Users(String userId, String fullName, String mobile, String password, String accountType, String ageGroup,
                 String keySkills, String about, String picture, String city, Long createdOn, Long updatedOn) {
        UserId = userId;
        FullName = fullName;
        Mobile = mobile;
        Password = password;
        AccountType = accountType;
        AgeGroup = ageGroup;
        KeySkills = keySkills;
        About = about;
        Picture = picture;
        City = city;
        CreatedOn = createdOn;
        UpdatedOn = updatedOn;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
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

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public Long getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Long createdOn) {
        CreatedOn = createdOn;
    }

    public Long getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        UpdatedOn = updatedOn;
    }
}
