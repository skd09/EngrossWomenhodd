package com.sharvari.engrosswomenhodd.Response.News;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class NewsList {

    String NewsId;
    String Title;
    String Story;
    String Picture;
    String CreatedOn;

    public NewsList(String newsId, String title, String story, String picture, String createdOn) {
        NewsId = newsId;
        Title = title;
        Story = story;
        Picture = picture;
        CreatedOn = createdOn;
    }

    public String getNewsId() {
        return NewsId;
    }

    public void setNewsId(String newsId) {
        NewsId = newsId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
