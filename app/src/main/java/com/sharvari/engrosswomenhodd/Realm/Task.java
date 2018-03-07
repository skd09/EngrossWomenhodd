package com.sharvari.engrosswomenhodd.Realm;

import io.realm.RealmObject;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class Task extends RealmObject {

    String TaskId;
    String UserId;
    String Title;
    String Description;
    String AddressId;
    String Price;
    String CategoryId;
    String IsComplete;
    Long Date;
    Long CreatedOn;
    Long UpdatedOn;

    public Task() {
    }


    public Task(String taskId, String userId, String title, String description, String addressId, String price, String categoryId, String isComplete, Long date, Long createdOn, Long updatedOn) {
        TaskId = taskId;
        UserId = userId;
        Title = title;
        Description = description;
        AddressId = addressId;
        Price = price;
        CategoryId = categoryId;
        IsComplete = isComplete;
        Date = date;
        CreatedOn = createdOn;
        UpdatedOn = updatedOn;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAddressId() {
        return AddressId;
    }

    public void setAddressId(String addressId) {
        AddressId = addressId;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getIsComplete() {
        return IsComplete;
    }

    public void setIsComplete(String isComplete) {
        IsComplete = isComplete;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
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
