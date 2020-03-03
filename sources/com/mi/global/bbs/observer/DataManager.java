package com.mi.global.bbs.observer;

import java.util.Observable;

public class DataManager extends Observable {
    private static DataManager dataManager;

    public static DataManager init() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public void userInfoChange(boolean z) {
        setChanged();
        notifyObservers(Boolean.valueOf(z));
    }

    public void notify(String str) {
        setChanged();
        notifyObservers(str);
    }
}
