package com.sharvari.engrosswomenhodd.Response.Category;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetCategoryResponse {

    String statusCode;
    String statusMsg;
    ArrayList<GetCategoryData> data;

    public GetCategoryResponse(String statusCode, String statusMsg, ArrayList<GetCategoryData> data) {
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

    public ArrayList<GetCategoryData> getData() {
        return data;
    }

    public void setData(ArrayList<GetCategoryData> data) {
        this.data = data;
    }
}
