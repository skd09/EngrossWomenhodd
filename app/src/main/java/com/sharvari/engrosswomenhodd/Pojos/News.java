package com.sharvari.engrosswomenhodd.Pojos;

/**
 * Created by sharvari on 28-Feb-18.
 */

public class News {

    String title, story, url, date;

    public News() {
    }

    public News(String title, String story, String url, String date) {
        this.title = title;
        this.story = story;
        this.url = url;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
