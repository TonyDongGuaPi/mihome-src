package com.alipay.mobile.security.bio.thread;

import com.alipay.mobile.security.bio.utils.BioLog;

public abstract class WatchThread extends Thread {
    public static final ThreadGroup tg = new ThreadGroup("watch-thread");

    /* renamed from: a  reason: collision with root package name */
    private boolean f1019a = true;
    private String b = null;

    /* access modifiers changed from: protected */
    public abstract void a();

    public WatchThread(String str) {
        super(tg, str);
        setDaemon(true);
    }

    public void kill() {
        this.f1019a = false;
    }

    public void run() {
        while (this.f1019a) {
            try {
                a();
            } catch (Exception e) {
                BioLog.e((Throwable) e);
            } catch (Throwable th) {
                BioLog.e(th);
            }
        }
    }

    public String getStatus() {
        return this.b;
    }
}
