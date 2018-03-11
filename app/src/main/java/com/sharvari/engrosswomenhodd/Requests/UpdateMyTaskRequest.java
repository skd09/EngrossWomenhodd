package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class UpdateMyTaskRequest {

    String taskId;
    String addressId;
    String title;
    String description;
    String amount;
    String endData;
    String categoryId;
    String isComplete;
    String ratings;

    public UpdateMyTaskRequest(String taskId, String addressId, String title, String description, String amount, String endData, String categoryId, String isComplete, String ratings) {
        this.taskId = taskId;
        this.addressId = addressId;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.endData = endData;
        this.categoryId = categoryId;
        this.isComplete = isComplete;
        this.ratings = ratings;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
