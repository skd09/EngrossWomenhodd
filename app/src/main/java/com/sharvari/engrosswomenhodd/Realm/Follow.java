package com.sharvari.engrosswomenhodd.Realm;

import io.realm.RealmObject;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class Follow extends RealmObject{

    String FollowId;
    String FollowerId;
    String FollowingId;
    String isActive;
    Long CreatedOn;

    public Follow() {
    }

    public Follow(String followId, String followerId, String followingId, String isActive, Long createdOn) {
        FollowId = followId;
        FollowerId = followerId;
        FollowingId = followingId;
        this.isActive = isActive;
        CreatedOn = createdOn;
    }

    public String getFollowId() {
        return FollowId;
    }

    public void setFollowId(String followId) {
        FollowId = followId;
    }

    public String getFollowerId() {
        return FollowerId;
    }

    public void setFollowerId(String followerId) {
        FollowerId = followerId;
    }

    public String getFollowingId() {
        return FollowingId;
    }

    public void setFollowingId(String followingId) {
        FollowingId = followingId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Long createdOn) {
        CreatedOn = createdOn;
    }
}
