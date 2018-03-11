package com.sharvari.engrosswomenhodd.Response.GetTaskResponse;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetTaskData {


    String TaskId;
    String UserId;
    String FullName;
    String Picture;
    String Title;
    String Description;
    String Country;
    String Area;
    String Price;
    String CreatedOn;
    String Date;

    public GetTaskData(String taskId, String userId, String fullName, String picture, String title, String description, String country, String area, String price, String createdOn, String date) {
        TaskId = taskId;
        UserId = userId;
        FullName = fullName;
        Picture = picture;
        Title = title;
        Description = description;
        Country = country;
        Area = area;
        Price = price;
        CreatedOn = createdOn;
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
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

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
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
