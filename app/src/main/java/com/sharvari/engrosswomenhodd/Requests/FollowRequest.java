package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class FollowRequest {


    String userId;
    String followingId;

    public FollowRequest(String userId, String followingId) {
        this.userId = userId;
        this.followingId = followingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }
}
