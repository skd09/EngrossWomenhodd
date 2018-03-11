package com.sharvari.engrosswomenhodd.Response.TaskRequest;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class TaskRequestResponse {


    String statusCode;
    String statusMsg;
    ArrayList<TaskRequestData> taskList;

    public TaskRequestResponse(String statusCode, String statusMsg, ArrayList<TaskRequestData> taskList) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.taskList = taskList;
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

    public ArrayList<TaskRequestData> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<TaskRequestData> taskList) {
        this.taskList = taskList;
    }
}
