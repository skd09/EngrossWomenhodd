package com.sharvari.engrosswomenhodd.Response.Feedback;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class GetFeedbackResponse {

    String statusCode;
    String statusMsg;
    @SerializedName("data")
    ArrayList<GetFeedbackList> list;

    public GetFeedbackResponse(String statusCode, String statusMsg, ArrayList<GetFeedbackList> list) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.list = list;
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

    public ArrayList<GetFeedbackList> getList() {
        return list;
    }

    public void setList(ArrayList<GetFeedbackList> list) {
        this.list = list;
    }
}
