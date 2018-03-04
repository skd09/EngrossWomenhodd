package com.sharvari.engrosswomenhodd.Realm;

import io.realm.RealmObject;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class UserType extends RealmObject {

    String TypeId;
    String Name;
    Long CreatedOn;

    public UserType() {
    }

    public UserType(String typeId, String name, Long createdOn) {
        TypeId = typeId;
        Name = name;
        CreatedOn = createdOn;
    }

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String typeId) {
        TypeId = typeId;
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
