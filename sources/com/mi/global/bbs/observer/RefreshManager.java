package com.mi.global.bbs.observer;

import java.util.Observable;

public class RefreshManager extends Observable {
    private static RefreshManager refreshManager;

    public static RefreshManager init() {
        if (refreshManager == null) {
            refreshManager = new RefreshManager();
        }
        return refreshManager;
    }

    public void startRefresh(boolean z) {
        setChanged();
        notifyObservers(Boolean.valueOf(z));
    }
}
