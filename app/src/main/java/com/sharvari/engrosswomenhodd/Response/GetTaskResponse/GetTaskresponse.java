package com.sharvari.engrosswomenhodd.Response.GetTaskResponse;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetTaskresponse {

    String statusCode;
    String statusMsg;
    ArrayList<GetTaskData> data;

    public GetTaskresponse(String statusCode, String statusMsg, ArrayList<GetTaskData> data) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
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

    public ArrayList<GetTaskData> getData() {
        return data;
    }

    public void setData(ArrayList<GetTaskData> data) {
        this.data = data;
    }
}
