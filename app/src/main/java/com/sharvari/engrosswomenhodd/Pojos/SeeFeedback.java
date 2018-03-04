package com.sharvari.engrosswomenhodd.Pojos;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class SeeFeedback {

    String name, date, feedback, image;

    public SeeFeedback(String name, String date, String feedback, String image) {
        this.name = name;
        this.date = date;
        this.feedback = feedback;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
