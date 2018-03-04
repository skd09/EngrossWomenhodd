package com.sharvari.engrosswomenhodd.Realm;

import io.realm.RealmObject;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class NewsFeed extends RealmObject {

    String NewsId;
    String Title;
    String Story;
    String Picture;
    Long CreatedOn;

    public NewsFeed() {
    }

    public NewsFeed(String newsId, String title, String story, String picture, Long createdOn) {
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

    public Long getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Long createdOn) {
        CreatedOn = createdOn;
    }
}
