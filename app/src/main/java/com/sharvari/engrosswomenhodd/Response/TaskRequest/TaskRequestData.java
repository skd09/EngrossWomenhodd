package com.sharvari.engrosswomenhodd.Response.TaskRequest;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class TaskRequestData {


    String TaskRequestId;
    String TaskId;
    String UserId;
    String FullName;
    String Price;
    String Comments;
    String IsAccepted;
    String CreatedOn;
    String Picture;

    public TaskRequestData(String taskRequestId, String taskId, String userId,
                           String fullName, String price, String comments, String isAccepted,
                           String createdOn, String picture) {
        TaskRequestId = taskRequestId;
        TaskId = taskId;
        UserId = userId;
        FullName = fullName;
        Price = price;
        Comments = comments;
        IsAccepted = isAccepted;
        CreatedOn = createdOn;
        Picture = picture;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getTaskRequestId() {
        return TaskRequestId;
    }

    public void setTaskRequestId(String taskRequestId) {
        TaskRequestId = taskRequestId;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getIsAccepted() {
        return IsAccepted;
    }

    public void setIsAccepted(String IsAccepted) {
        this.IsAccepted = IsAccepted;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
