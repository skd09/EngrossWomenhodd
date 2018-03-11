package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class UploadNewsRequest {

    String title;
    String story;
    String url;

    public UploadNewsRequest(String title, String story, String url) {
        this.title = title;
        this.story = story;
        this.url = url;
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
