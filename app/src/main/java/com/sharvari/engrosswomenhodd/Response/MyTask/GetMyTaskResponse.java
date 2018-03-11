package com.sharvari.engrosswomenhodd.Response.MyTask;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetMyTaskResponse {


    String statusCode;
    String statusMsg;
    ArrayList<GetMyTaskData> data;

    public GetMyTaskResponse(String statusCode, String statusMsg, ArrayList<GetMyTaskData> data) {
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

    public ArrayList<GetMyTaskData> getData() {
        return data;
    }

    public void setData(ArrayList<GetMyTaskData> data) {
        this.data = data;
    }
}
