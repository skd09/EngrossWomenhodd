package com.sharvari.engrosswomenhodd.Response.GetBusiness;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetBusinessResponse {

    String statusCode;
    String statusMsg;
    ArrayList<GetBusinessData> data;

    public GetBusinessResponse(String statusCode, String statusMsg, ArrayList<GetBusinessData> data) {
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

    public ArrayList<GetBusinessData> getData() {
        return data;
    }

    public void setData(ArrayList<GetBusinessData> data) {
        this.data = data;
    }
}
