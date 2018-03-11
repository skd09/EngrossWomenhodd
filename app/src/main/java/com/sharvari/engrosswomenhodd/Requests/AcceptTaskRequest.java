package com.sharvari.engrosswomenhodd.Requests;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class AcceptTaskRequest {

    String taskRequestId;

    public AcceptTaskRequest(String taskRequestId) {
        this.taskRequestId = taskRequestId;
    }

    public String getTaskRequestId() {
        return taskRequestId;
    }

    public void setTaskRequestId(String taskRequestId) {
        this.taskRequestId = taskRequestId;
    }
}
