package com.tiqiaa.icontrol.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakRefHandler {
    private static final String TAG = "WeakRefHandler";
    /* access modifiers changed from: private */
    public static List<WeakReference<Object>> bitmapWeakRefList;
    private static Thread cleanThread;
    /* access modifiers changed from: private */
    public static boolean stop;

    static {
        init();
    }

    private static void init() {
        stop = false;
        if (bitmapWeakRefList == null) {
            bitmapWeakRefList = new ArrayList();
        }
        if (cleanThread == null) {
            cleanThread = new Thread(new Runnable() {
                public void run() {
                    while (!WeakRefHandler.stop) {
                        if (WeakRefHandler.bitmapWeakRefList.size() > 5) {
                            WeakRefHandler.clear();
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            cleanThread.start();
        }
    }

    public static void add(Object obj) {
        if (bitmapWeakRefList == null || cleanThread == null) {
            init();
        }
        bitmapWeakRefList.add(new WeakReference(obj));
    }

    public static synchronized void clear() {
        synchronized (WeakRefHandler.class) {
            int i = 0;
            for (int size = bitmapWeakRefList.size() - 1; size >= 0; size--) {
                WeakReference weakReference = bitmapWeakRefList.get(size);
                if (weakReference == null || weakReference.get() == null) {
                    bitmapWeakRefList.remove(size);
                } else {
                    i++;
                }
            }
            LogUtil.e(TAG, "notRecycled count = " + i);
            System.gc();
        }
    }

    public static void stop() {
        stop = true;
    }
}
