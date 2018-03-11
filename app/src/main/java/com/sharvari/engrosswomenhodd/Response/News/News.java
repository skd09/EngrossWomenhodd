package com.sharvari.engrosswomenhodd.Response.News;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class News {

    String statusCode;
    String statusMsg;

    @SerializedName("data")
    ArrayList<NewsList> list;

    public News(String statusCode, String statusMsg, ArrayList<NewsList> list) {
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

    public ArrayList<NewsList> getList() {
        return list;
    }

    public void setList(ArrayList<NewsList> list) {
        this.list = list;
    }
}
