package com.sharvari.engrosswomenhodd.Response.UserDetails;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class GetUserDetails {


    String statusCode;
    String statusMsg;
    ArrayList<GetUserData> data;

    public GetUserDetails(String statusCode, String statusMsg, ArrayList<GetUserData> data) {
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

    public ArrayList<GetUserData> getData() {
        return data;
    }

    public void setData(ArrayList<GetUserData> data) {
        this.data = data;
    }
}
