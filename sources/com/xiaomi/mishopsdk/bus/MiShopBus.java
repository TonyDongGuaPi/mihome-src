package com.xiaomi.mishopsdk.bus;

import android.os.Bundle;
import android.os.Looper;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class MiShopBus {
    private static final String TAG = "MiShopBus";
    private static MiShopBus sDefaultInstance;
    private Map<Integer, Map<Integer, Event>> mEventMap = new HashMap();

    public static MiShopBus getDefault() {
        if (sDefaultInstance == null) {
            synchronized (MiShopBus.class) {
                if (sDefaultInstance == null) {
                    sDefaultInstance = new MiShopBus();
                }
            }
        }
        return sDefaultInstance;
    }

    public synchronized void registerCurThreadEvent(Event event) {
        event.mEventType = 1;
        registerEvent(event);
    }

    public synchronized void registerMainThreadEvent(Event event) {
        event.mEventType = 2;
        registerEvent(event);
    }

    private synchronized void registerEvent(Event event) {
        Map map;
        if (this.mEventMap.containsKey(Integer.valueOf(event.mEventId))) {
            map = this.mEventMap.get(Integer.valueOf(event.mEventId));
        } else {
            map = new HashMap();
            this.mEventMap.put(Integer.valueOf(event.mEventId), map);
        }
        map.put(Integer.valueOf(event.mHostHashCode), event);
    }

    public synchronized boolean isRegistered(Object obj, int i) {
        boolean z;
        z = false;
        if (this.mEventMap.containsKey(Integer.valueOf(i))) {
            if (this.mEventMap.get(Integer.valueOf(i)).containsKey(Integer.valueOf(obj.hashCode()))) {
                z = true;
            }
        }
        return z;
    }

    public synchronized void unregister(Object obj, int i) {
        if (this.mEventMap.containsKey(Integer.valueOf(i))) {
            int hashCode = obj.hashCode();
            Map map = this.mEventMap.get(Integer.valueOf(i));
            if (map == null || !map.containsKey(Integer.valueOf(hashCode))) {
                Log.e(TAG, "unregisger an event not registered, host=%s", (Object) Integer.valueOf(obj.hashCode()));
                if (Log.isDebug()) {
                    throw new RuntimeException("post an event not registered, host=" + obj.hashCode());
                }
            } else {
                ((Event) map.remove(Integer.valueOf(hashCode))).mCanceled = true;
                if (map.size() == 0) {
                    this.mEventMap.remove(Integer.valueOf(i));
                }
            }
        } else {
            Log.e(TAG, "unregisger an event not registered, eventId=%s", (Object) Integer.valueOf(i));
            if (Log.isDebug()) {
                throw new RuntimeException("post an event not registered, eventId=" + i);
            }
        }
    }

    public void post(int i, long j) {
        post(i, j, (Bundle) null);
    }

    public void post(int i, Bundle bundle) {
        post(i, -1, bundle);
    }

    public void post(int i, long j, Bundle bundle) {
        Collection values;
        synchronized (this) {
            values = this.mEventMap.containsKey(Integer.valueOf(i)) ? this.mEventMap.get(Integer.valueOf(i)).values() : null;
        }
        if (values != null) {
            execEvents(values, j, bundle);
            return;
        }
        Log.e(TAG, "post an event not registered, eventId=%s", (Object) Integer.valueOf(i));
        if (Log.isDebug()) {
            throw new RuntimeException("post an event not registered, eventId=" + i);
        }
    }

    private void execEvents(Collection<Event> collection, long j, Bundle bundle) {
        if (collection == null || collection.size() == 0) {
            Log.e(TAG, "execEvents: get an event not registered, exit");
            return;
        }
        boolean z = Looper.getMainLooper() == Looper.myLooper();
        for (Event next : collection) {
            next.mParam1 = j;
            next.mParam2 = bundle;
            if (next.mEventType == 1 || (z && next.mEventType == 2)) {
                next.run();
            } else if (next.mEventType == 2) {
                AndroidUtil.runOnUIThread(next);
            } else {
                Log.e(TAG, "execEvents, event type is not valid");
                if (Log.isDebug()) {
                    throw new RuntimeException("this code should not be invoked");
                }
            }
        }
    }

    public void dumpEventLeak() {
        if (this.mEventMap.size() == 0) {
            Log.d(TAG, "perfect, there is no leaked events.");
            return;
        }
        for (Map.Entry next : this.mEventMap.entrySet()) {
            int intValue = ((Integer) next.getKey()).intValue();
            Map map = (Map) next.getValue();
            if (map.size() != 0) {
                Log.e(TAG, "detect an event leak, eventId=%s:", (Object) Integer.valueOf(intValue));
                for (Map.Entry entry : map.entrySet()) {
                    Log.e(TAG, "\tleak host hashCode=%s, \n\t\tevent={%s}", entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
