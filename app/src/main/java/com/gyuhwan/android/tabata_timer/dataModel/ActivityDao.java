package com.gyuhwan.android.tabata_timer.dataModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ActivityDao {
    private Realm realm;
    private Class table=Activity.class;

    public ActivityDao(Realm realm) {
        this.realm=realm;
    }

    public void create(Activity activity){
        realm.insertOrUpdate(activity);
    }

    public void delete(Activity activity){
        activity.deleteFromRealm();
    }

    public RealmResults<Activity> readAll(){
        return realm.where(table).findAll();
    }

    public ArrayList<Activity> realAlltoList(){
        RealmResults<Activity> result=readAll();
        ArrayList<Activity> ret=new ArrayList<>();
        for(int i=0;i<result.size();i++){
            ret.add(result.get(i));
        }
        return ret;
    }

    public RealmResults<Activity> readFromName(String name){
        return realm.where(table).equalTo("name",name).findAll();
    }
}
