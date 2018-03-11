package com.sharvari.engrosswomenhodd.Pojos;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class Business {

    String id,company, description, name, url, date, userId;


    public Business(String id, String company, String description, String name, String url, String date, String userId) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.name = name;
        this.url = url;
        this.date = date;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
