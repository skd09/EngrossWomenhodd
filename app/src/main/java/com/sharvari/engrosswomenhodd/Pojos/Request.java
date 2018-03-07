package com.sharvari.engrosswomenhodd.Pojos;

/**
 * Created by sharvaridivekar on 02/03/18.
 */

public class Request {

    String id,userId, name, status, date, price, description, picture;

    public Request(String id, String userId, String name, String status, String date, String price, String description, String picture) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.status = status;
        this.date = date;
        this.price = price;
        this.description = description;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
