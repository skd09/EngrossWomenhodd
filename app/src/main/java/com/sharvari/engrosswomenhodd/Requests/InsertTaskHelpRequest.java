package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class InsertTaskHelpRequest {


    String taskId;
    String userId;
    String price;
    String comments;

    public InsertTaskHelpRequest(String taskId, String userId, String price, String comments) {
        this.taskId = taskId;
        this.userId = userId;
        this.price = price;
        this.comments = comments;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
