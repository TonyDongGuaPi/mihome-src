package com.xiaomi.smarthome.library.common.util;

import android.util.SparseArray;
import java.lang.ref.WeakReference;

public class EventDispatcher {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18672a = 1;
    private static SparseArray<WeakReference<IEventCaller>> b = new SparseArray<>();

    public interface IEventCaller {
        int a();

        void a(String str, Object obj);
    }

    public static synchronized void a(IEventCaller iEventCaller) {
        synchronized (EventDispatcher.class) {
            if (iEventCaller != null) {
                try {
                    b.put(iEventCaller.a(), new WeakReference(iEventCaller));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public static synchronized void b(IEventCaller iEventCaller) {
        synchronized (EventDispatcher.class) {
            if (iEventCaller != null) {
                try {
                    b.remove(iEventCaller.a());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public static synchronized void a(int i) {
        synchronized (EventDispatcher.class) {
            a(i, (String) null);
        }
    }

    public static synchronized void a(int i, String str) {
        synchronized (EventDispatcher.class) {
            a(i, str, (Object) null);
        }
    }

    public static synchronized void a(int i, String str, Object obj) {
        IEventCaller iEventCaller;
        synchronized (EventDispatcher.class) {
            try {
                WeakReference weakReference = b.get(i);
                if (!(weakReference == null || (iEventCaller = (IEventCaller) weakReference.get()) == null)) {
                    iEventCaller.a(str, obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }
}
