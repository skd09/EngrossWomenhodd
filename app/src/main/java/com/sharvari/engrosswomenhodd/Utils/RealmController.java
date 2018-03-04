package com.sharvari.engrosswomenhodd.Utils;

import android.app.Activity;
import android.app.Application;
import android.icu.text.SimpleDateFormat;
import android.support.v4.app.Fragment;

import com.sharvari.engrosswomenhodd.Pojos.SeeFeedback;
import com.sharvari.engrosswomenhodd.Realm.Feedback;
import com.sharvari.engrosswomenhodd.Realm.Follow;
import com.sharvari.engrosswomenhodd.Realm.NewsFeed;
import com.sharvari.engrosswomenhodd.Realm.TaskRequest;
import com.sharvari.engrosswomenhodd.Realm.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by sharvaridivekar on 04/03/18.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    public Users getCustomerDetails(String userId){
        return realm.where(Users.class).equalTo("UserId",userId).findFirst();
    }

    public void shareFeedback(String desc, String userId){
        realm.beginTransaction();
        Feedback feedback = new Feedback(
            UUID.randomUUID().toString(),
            userId, "0",
            desc,
            System.currentTimeMillis()
        );
        realm.insert(feedback);
        realm.commitTransaction();
    }


    public void uploadNews(String title, String story, String url){
        realm.beginTransaction();
        NewsFeed newsFeed = new NewsFeed(
            UUID.randomUUID().toString(),
            title,
            story,
            url,
            System.currentTimeMillis()
        );
        realm.insert(newsFeed);
        realm.commitTransaction();
    }

    public RealmResults<NewsFeed> getUploadNews(){
        return realm.where(NewsFeed.class).findAll();
    }

    public ArrayList<SeeFeedback> getFeedbackList(){
        ArrayList<SeeFeedback> seeFeedbacks = new ArrayList<>();
        RealmResults<Feedback> feedbackRealmResults = realm.where(Feedback.class).findAll();

        for(Feedback f : feedbackRealmResults){
            Users u = realm.where(Users.class).equalTo("UserId",f.getUserId()).findFirst();
            SeeFeedback s = new SeeFeedback(u.getFullName(),dateFormat(f.getCreatedOn()),f.getMessage(),u.getPicture());
            seeFeedbacks.add(s);
        }

        return seeFeedbacks;
    }

    public String getProfileData(String userId, String type){
        String data = "";
        if(type.equals("followers")){
            data = realm.where(Follow.class).equalTo("FollowerId",userId)
                    .and().equalTo("isActive","1").findAll().size() +"";
        }else if(type.equals("following")){
            data = realm.where(Follow.class).equalTo("FollowingId",userId)
                    .and().equalTo("isActive","1").findAll().size() +"";
        }else if(type.equals("totalTask")){
            data = realm.where(TaskRequest.class).equalTo("UserId",userId)
                    .and().equalTo("IsAccepted","1").findAll().size()+"";
        }
        return data;
    }

    public void updateProfileData(String userId,String name, String mobile, String skills, String about, String type){
        realm.beginTransaction();
        Users users =  realm.where(Users.class).equalTo("UserId",userId).findFirst();
        users.setFullName(name);
        users.setAbout(about);
        users.setKeySkills(skills);
        users.setAccountType(type);
        users.setMobile(mobile);
        realm.copyToRealmOrUpdate(users);
        realm.commitTransaction();
    }

    private String dateFormat(Long date){
        Date d = new Date(date);
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(d);
    }

}
