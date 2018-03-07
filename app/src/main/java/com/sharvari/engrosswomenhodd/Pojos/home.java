package com.sharvari.engrosswomenhodd.Pojos;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class home {
    String id, picture, name, leftDays, request, title, description, amount, location;


    public home(String id, String picture, String name, String leftDays, String request, String title, String description, String amount, String location) {
        this.id = id;
        this.picture = picture;
        this.name = name;
        this.leftDays = leftDays;
        this.request = request;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(String leftDays) {
        this.leftDays = leftDays;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
