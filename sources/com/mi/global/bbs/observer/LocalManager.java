package com.mi.global.bbs.observer;

import java.util.Observable;

public class LocalManager extends Observable {
    private static LocalManager localManager;

    public static LocalManager init() {
        if (localManager == null) {
            localManager = new LocalManager();
        }
        return localManager;
    }

    public void localChange(boolean z) {
        setChanged();
        notifyObservers(Boolean.valueOf(z));
    }
}
