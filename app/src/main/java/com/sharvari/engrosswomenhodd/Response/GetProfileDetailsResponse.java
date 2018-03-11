package com.sharvari.engrosswomenhodd.Response;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetProfileDetailsResponse {
    String statusCode;
    String statusMsg;
    String taskDone;
    String followersCount;
    String followingCount;

    public GetProfileDetailsResponse(String statusCode, String statusMsg, String taskDone, String followersCount, String followingCount) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.taskDone = taskDone;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getTaskDone() {
        return taskDone;
    }

    public void setTaskDone(String taskDone) {
        this.taskDone = taskDone;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(String followingCount) {
        this.followingCount = followingCount;
    }
}
