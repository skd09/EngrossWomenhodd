package com.sharvari.engrosswomenhodd.Response.Feedback;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class GetFeedbackList {

    String FeedbackId;
    String UserId;
    String Ratings;
    String Message;
    String CreatedOn;

    public GetFeedbackList(String feedbackId, String userId, String ratings, String message, String createdOn) {
        FeedbackId = feedbackId;
        UserId = userId;
        Ratings = ratings;
        Message = message;
        CreatedOn = createdOn;
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

    public String getRatings() {
        return Ratings;
    }

    public void setRatings(String ratings) {
        Ratings = ratings;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
