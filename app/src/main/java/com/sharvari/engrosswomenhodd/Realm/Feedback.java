package com.sharvari.engrosswomenhodd.Realm;

import io.realm.RealmObject;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class Feedback extends RealmObject {

    String FeedbackId;
    String UserId;
    String Ratings;
    String Message;
    Long CreatedOn;

    public Feedback() {
    }

    public Feedback(String feedbackId, String userId, String ratings, String message, Long createdOn) {
        FeedbackId = feedbackId;
        UserId = userId;
        Ratings = ratings;
        Message = message;
        CreatedOn = createdOn;
    }

    public String getRatings() {
        return Ratings;
    }

    public void setRatings(String ratings) {
        Ratings = ratings;
    }

    public String getFeedbackId() {
        return FeedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        FeedbackId = feedbackId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Long getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Long createdOn) {
        CreatedOn = createdOn;
    }
}
