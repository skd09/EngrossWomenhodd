package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class UpdateUserRequest {

    String userId;
    String userType;
    String skills;
    String about;

    public UpdateUserRequest(String userId, String userType, String skills, String about) {
        this.userId = userId;
        this.userType = userType;
        this.skills = skills;
        this.about = about;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
