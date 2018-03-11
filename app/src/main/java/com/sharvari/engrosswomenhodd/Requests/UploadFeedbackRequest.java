package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class UploadFeedbackRequest {


    String userId;
    String description;

    public UploadFeedbackRequest(String userId, String description) {
        this.userId = userId;
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
