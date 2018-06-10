package com.gyuhwan.android.tabata_timer.dataModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RoutineDao {

    private Realm realm;
    private Class table=Routine.class;
    public RoutineDao(Realm realm) {
        this.realm=realm;
    }

    public void create(Routine routine){
        realm.insertOrUpdate(routine);
    }

    public void delete(Routine routine){
        routine.deleteFromRealm();
    }

    public RealmResults<Routine> readAll(){
        return realm.where(table).findAll();
    }

    public ArrayList<Routine> realAlltoList(){
        RealmResults<Routine> result=readAll();
        ArrayList<Routine> ret=new ArrayList<>();
        for(int i=0;i<result.size();i++){
            ret.add(result.get(i));
        }
        return ret;
    }

    public RealmResults<Routine> readFromName(String name){
        return realm.where(table).equalTo("name",name).findAll();
    }

    public boolean isExistFromName(String name){
        return readFromName(name).size()!=0;
    }

}
