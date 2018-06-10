package com.gyuhwan.android.tabata_timer.dataModel;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Routine extends RealmObject {

    @PrimaryKey
    private String name;

    private RealmList<Activity> activities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(RealmList<Activity> activities) {
        this.activities = activities;
    }
}
