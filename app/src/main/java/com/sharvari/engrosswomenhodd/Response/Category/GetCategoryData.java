package com.sharvari.engrosswomenhodd.Response.Category;

/**
 * Created by sharvaridivekar on 11/03/18.
 */

public class GetCategoryData {

    String CategoryId;
    String Name;
    String CreatedOn;

    public GetCategoryData(String categoryId, String name, String createdOn) {
        CategoryId = categoryId;
        Name = name;
        CreatedOn = createdOn;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
