package com.sharvari.engrosswomenhodd.Response.GetBusiness;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetBusinessData {


    String BusinessId;
    String UserId;
    String CompanyName;
    String Description;
    String URL;
    String CreatedOn;
    String FullName;


    public GetBusinessData(String businessId, String userId, String companyName, String description, String URL, String createdOn, String fullName) {
        BusinessId = businessId;
        UserId = userId;
        CompanyName = companyName;
        Description = description;
        this.URL = URL;
        CreatedOn = createdOn;
        FullName = fullName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(String businessId) {
        BusinessId = businessId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
