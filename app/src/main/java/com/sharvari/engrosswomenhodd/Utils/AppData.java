package com.sharvari.engrosswomenhodd.Utils;

import android.content.Context;

import com.sharvari.engrosswomenhodd.Realm.Category;
import com.sharvari.engrosswomenhodd.Realm.UserType;
import com.sharvari.engrosswomenhodd.Realm.Users;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class AppData {

    private Realm realm;

    public void setData(Context context){
        realm = Realm.getDefaultInstance();
        if(realm.where(UserType.class).findAll().size()==0) {
            setUserType();
            setCategory();
            setAdminData();
        }
    }

    public void setUserType()
    {
        realm.beginTransaction();
        ArrayList<UserType> userTypes = new ArrayList<>();
        UserType type = new UserType(UUID.randomUUID().toString(),"Personal",System.currentTimeMillis());
        userTypes.add(type);

        type = new UserType(UUID.randomUUID().toString(),"Entrepreneur",System.currentTimeMillis());
        userTypes.add(type);

        type = new UserType(UUID.randomUUID().toString(),"Both",System.currentTimeMillis());
        userTypes.add(type);

        realm.copyToRealmOrUpdate(userTypes);
        realm.commitTransaction();
    }

    public void setCategory()
    {
        realm.beginTransaction();
        ArrayList<Category> categories = new ArrayList<>();
        Category type = new Category(UUID.randomUUID().toString(),"Cooking",System.currentTimeMillis());
        categories.add(type);

        type = new Category(UUID.randomUUID().toString(),"Cleaning",System.currentTimeMillis());
        categories.add(type);

        type = new Category(UUID.randomUUID().toString(),"Helper",System.currentTimeMillis());
        categories.add(type);

        type = new Category(UUID.randomUUID().toString(),"Nanny",System.currentTimeMillis());
        categories.add(type);

        type = new Category(UUID.randomUUID().toString(),"Shopping",System.currentTimeMillis());
        categories.add(type);

        type = new Category(UUID.randomUUID().toString(),"Teaching",System.currentTimeMillis());
        categories.add(type);

        type = new Category(UUID.randomUUID().toString(),"Others",System.currentTimeMillis());
        categories.add(type);

        realm.copyToRealmOrUpdate(categories);
        realm.commitTransaction();
    }


    public void setAdminData()
    {
        realm.beginTransaction();
        Users users = new Users(
                UUID.randomUUID().toString(),
                "Sharvari Divekar",
                "9999900000",
                "skd",
                "Admin",
                "23",
                "Developer",
                "I am full stack developer and ready to do any task.",
                "1",
                "Mumbai",
                System.currentTimeMillis(),
                System.currentTimeMillis()
        );
        realm.copyToRealmOrUpdate(users);
        realm.commitTransaction();
    }

}
