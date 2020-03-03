package com.xiaomi.smarthome.framework.page;

import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.corereceiver.ActivityListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public enum ActivityStack {
    instance;
    
    private ConcurrentLinkedQueue<ActivityListener> mActivityListeners;

    public ConcurrentLinkedQueue<ActivityListener> getActivityListeners() {
        return this.mActivityListeners;
    }

    public void registerActivityListener(ActivityListener activityListener) {
        this.mActivityListeners.add(activityListener);
    }

    public void unregisterActivityListener(ActivityListener activityListener) {
        this.mActivityListeners.remove(activityListener);
    }

    public void doClearOnServerChanged() {
        HashSet<ActivityListener> hashSet = new HashSet<>();
        Iterator<ActivityListener> it = this.mActivityListeners.iterator();
        while (it.hasNext()) {
            ActivityListener next = it.next();
            try {
                next.onServerChanged();
            } catch (Exception unused) {
                hashSet.add(next);
            }
        }
        for (ActivityListener remove : hashSet) {
            this.mActivityListeners.remove(remove);
        }
        CoreApi.a().K();
    }
}
