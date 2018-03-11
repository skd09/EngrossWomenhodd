package com.sharvari.engrosswomenhodd.Response.GetAddress;

import com.sharvari.engrosswomenhodd.Response.Category.GetCategoryData;

import java.util.ArrayList;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetAddressResponse {

    String statusCode;
    String statusMsg;
    ArrayList<GetAddressData> data;

    public GetAddressResponse(String statusCode, String statusMsg, ArrayList<GetAddressData> data) {
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

    public ArrayList<GetAddressData> getData() {
        return data;
    }

    public void setData(ArrayList<GetAddressData> data) {
        this.data = data;
    }
}
