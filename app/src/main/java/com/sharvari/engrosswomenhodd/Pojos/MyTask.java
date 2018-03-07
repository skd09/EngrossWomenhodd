package com.sharvari.engrosswomenhodd.Pojos;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class MyTask {

    String id, name, date, description,status, location, amount, requestCount;

    public MyTask(String id, String name, String date, String description, String status,
                  String location, String amount, String requestCount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.status = status;
        this.location = location;
        this.amount = amount;
        this.requestCount = requestCount;
    }

    public String getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(String requestCount) {
        this.requestCount = requestCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
