package com.sharvari.engrosswomenhodd.Realm;

import io.realm.RealmObject;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class TaskRequest extends RealmObject {

    String TaskRequestId;
    String TaskId;
    String UserId;
    String Price;
    String Comments;
    String Ratings;
    String IsAccepted;
    Long CreatedOn;
    Long UpdatedOn;

    public TaskRequest() {
    }

    public TaskRequest(String taskRequestId, String taskId, String userId, String price,
                       String comments, String ratings, String isAccepted, Long createdOn, Long updatedOn) {
        TaskRequestId = taskRequestId;
        TaskId = taskId;
        UserId = userId;
        Price = price;
        Comments = comments;
        Ratings = ratings;
        IsAccepted = isAccepted;
        CreatedOn = createdOn;
        UpdatedOn = updatedOn;
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

    public String getRatings() {
        return Ratings;
    }

    public void setRatings(String ratings) {
        Ratings = ratings;
    }

    public String getIsAccepted() {
        return IsAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        IsAccepted = isAccepted;
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
