package com.sharvari.engrosswomenhodd.Response;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class UserRegisterResponse {
    String statusCode;
    String statusMsg;
    String userId;

    public UserRegisterResponse(String statusCode, String statusMsg, String userId) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.userId = userId;
    }


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
