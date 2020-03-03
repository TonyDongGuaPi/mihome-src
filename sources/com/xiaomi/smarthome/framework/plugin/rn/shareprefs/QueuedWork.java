package com.xiaomi.smarthome.framework.plugin.rn.shareprefs;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import java.util.Iterator;
import java.util.LinkedList;

public class QueuedWork {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17494a = "QueuedWork";
    private static final boolean b = false;
    private static final long c = 100;
    private static final long d = 512;
    private static final Object e = new Object();
    private static Object f = new Object();
    private static final LinkedList<Runnable> g = new LinkedList<>();
    private static Handler h = null;
    private static final LinkedList<Runnable> i = new LinkedList<>();
    private static boolean j = true;
    private static final ExponentiallyBucketedHistogram k = new ExponentiallyBucketedHistogram(16);
    private static int l = 0;

    private static Handler d() {
        Handler handler;
        synchronized (e) {
            if (h == null) {
                HandlerThread handlerThread = new HandlerThread("queued-work-looper", -2);
                handlerThread.start();
                h = new QueuedWorkHandler(handlerThread.getLooper());
            }
            handler = h;
        }
        return handler;
    }

    public static void a(Runnable runnable) {
        synchronized (e) {
            g.add(runnable);
        }
    }

    public static void b(Runnable runnable) {
        synchronized (e) {
            g.remove(runnable);
        }
    }

    public static void a() {
        Runnable poll;
        long currentTimeMillis = System.currentTimeMillis();
        Handler d2 = d();
        synchronized (e) {
            if (d2.hasMessages(1)) {
                d2.removeMessages(1);
            }
            j = false;
        }
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            e();
            while (true) {
                try {
                    synchronized (e) {
                        poll = g.poll();
                    }
                    if (poll == null) {
                        j = true;
                        synchronized (e) {
                            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                            if (currentTimeMillis2 > 0) {
                                k.a(Long.valueOf(currentTimeMillis2).intValue());
                                l++;
                                if (l % 1024 == 0 || currentTimeMillis2 > 512) {
                                    k.a(f17494a, "waited: ");
                                }
                            }
                        }
                        return;
                    }
                    poll.run();
                } catch (Throwable th) {
                    j = true;
                    throw th;
                }
            }
            while (true) {
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    public static void a(Runnable runnable, boolean z) {
        Handler d2 = d();
        synchronized (e) {
            i.add(runnable);
            if (!z || !j) {
                d2.sendEmptyMessage(1);
            } else {
                d2.sendEmptyMessageDelayed(1, 100);
            }
        }
    }

    public static boolean b() {
        boolean z;
        synchronized (e) {
            z = !i.isEmpty();
        }
        return z;
    }

    /* access modifiers changed from: private */
    public static void e() {
        LinkedList linkedList;
        synchronized (f) {
            synchronized (e) {
                linkedList = (LinkedList) i.clone();
                i.clear();
                d().removeMessages(1);
            }
            if (linkedList.size() > 0) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
            }
        }
    }

    private static class QueuedWorkHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        static final int f17495a = 1;

        QueuedWorkHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                QueuedWork.e();
            }
        }
    }
}
