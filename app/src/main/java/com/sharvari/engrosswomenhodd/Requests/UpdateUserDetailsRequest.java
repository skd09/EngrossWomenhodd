package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class UpdateUserDetailsRequest {


    String userId;
    String userType;
    String skills;
    String url;

    public UpdateUserDetailsRequest(String userId, String userType, String skills, String url) {
        this.userId = userId;
        this.userType = userType;
        this.skills = skills;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
