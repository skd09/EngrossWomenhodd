package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class GetTaskRequest {


    String userId;

    public GetTaskRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
