package com.gyuhwan.android.tabata_timer.dataModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Activity extends RealmObject {

    @PrimaryKey
    private String name;

    private long playtime;
    private long resttime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPlaytime() {
        return playtime;
    }

    public void setPlaytime(long playtime) {
        this.playtime = playtime;
    }

    public long getResttime() {
        return resttime;
    }

    public void setResttime(long resttime) {
        this.resttime = resttime;
    }

}
