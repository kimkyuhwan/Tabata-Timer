package com.gyuhwan.android.tabata_timer.tool;

import android.arch.lifecycle.ViewModel;

import com.gyuhwan.android.tabata_timer.dataModel.Routine;
import com.gyuhwan.android.tabata_timer.dataModel.RoutineDao;

import java.util.ArrayList;

import io.realm.Realm;

public class RoutineViewModel extends ViewModel {

    private Realm realm=Realm.getDefaultInstance();
    private RoutineDao routineDao=new RoutineDao(realm);

    @Override
    protected void onCleared() {
        realm.close();
        super.onCleared();
    }

    public ArrayList<Routine> getAllRoutine(){
        return routineDao.realAlltoList();
    }

    public void createRoutine(Routine routine){
        routineDao.create(routine);
    }

    public boolean isExistFromName(String name){ return routineDao.isExistFromName(name);}

}
