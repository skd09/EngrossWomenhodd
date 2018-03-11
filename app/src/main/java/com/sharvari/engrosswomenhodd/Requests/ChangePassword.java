package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class ChangePassword {


    String userId;
    String password;

    public ChangePassword(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
