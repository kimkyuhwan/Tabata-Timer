package com.gyuhwan.android.tabata_timer;

import android.app.Application;

import io.realm.Realm;

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm realm=Realm.getDefaultInstance();
    }
}
