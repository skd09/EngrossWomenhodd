package com.sharvari.engrosswomenhodd.Realm;

import io.realm.RealmObject;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class Category extends RealmObject {

    String CategoryId;
    String Name;
    Long CreatedOn;

    public Category() {
    }

    public Category(String categoryId, String name, Long createdOn) {
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

    public Long getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Long createdOn) {
        CreatedOn = createdOn;
    }
}
