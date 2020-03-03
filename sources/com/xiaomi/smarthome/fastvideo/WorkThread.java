package com.xiaomi.smarthome.fastvideo;

import android.util.Log;
import com.mi.global.bbs.utils.Constants;

public abstract class WorkThread extends Thread {
    protected volatile boolean i = false;

    /* access modifiers changed from: protected */
    public abstract int d() throws InterruptedException;

    /* access modifiers changed from: protected */
    public abstract void e();

    /* access modifiers changed from: protected */
    public abstract void f();

    public WorkThread(String str) {
        super(str);
        setPriority(1);
    }

    public void run() {
        e();
        while (this.i) {
            try {
                d();
            } catch (Throwable unused) {
            }
        }
        f();
        Log.e(Constants.TitleMenu.TYPE_FAVORITE, "[Thread quit]" + getName());
    }

    public synchronized void start() {
        if (!this.i) {
            this.i = true;
            super.start();
        }
    }

    public synchronized void g() {
        if (this.i) {
            this.i = false;
            interrupt();
        }
    }

    public synchronized void h() {
        if (this.i) {
            this.i = false;
            interrupt();
            try {
                join();
            } catch (InterruptedException unused) {
            }
        }
    }

    public boolean i() {
        return this.i;
    }
}
