package com.sharvari.engrosswomenhodd.Response.MyTask;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetMyTaskData {

    String UserId;
    String TaskId;
    String FullName;
    String Date;
    String Description;
    String IsComplete;
    String Area;
    String Country;
    String Price;
    String CreatedOn;
    String Title;
    String AddressId;

    public GetMyTaskData(String userId, String taskId, String fullName, String date, String description, String isComplete, String area, String country, String price, String createdOn, String title, String addressId) {
        UserId = userId;
        TaskId = taskId;
        FullName = fullName;
        Date = date;
        Description = description;
        IsComplete = isComplete;
        Area = area;
        Country = country;
        Price = price;
        CreatedOn = createdOn;
        Title = title;
        AddressId = addressId;
    }

    public String getAddressId() {
        return AddressId;
    }

    public void setAddressId(String addressId) {
        AddressId = addressId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIsComplete() {
        return IsComplete;
    }

    public void setIsComplete(String isComplete) {
        IsComplete = isComplete;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
