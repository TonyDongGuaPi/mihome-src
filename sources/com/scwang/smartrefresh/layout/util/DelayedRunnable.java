package com.scwang.smartrefresh.layout.util;

public class DelayedRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public long f8800a;
    private Runnable b = null;

    public DelayedRunnable(Runnable runnable, long j) {
        this.b = runnable;
        this.f8800a = j;
    }

    public void run() {
        try {
            if (this.b != null) {
                this.b.run();
                this.b = null;
            }
        } catch (Throwable th) {
            if (!(th instanceof NoClassDefFoundError)) {
                th.printStackTrace();
            }
        }
    }
}
