package com.xiaomi.smarthome.audioprocess;

import android.util.Log;
import com.mi.global.bbs.utils.Constants;

public abstract class WorkThread extends Thread {
    protected volatile boolean mIsRunning = false;

    /* access modifiers changed from: protected */
    public abstract void doInitial();

    /* access modifiers changed from: protected */
    public abstract void doRelease();

    /* access modifiers changed from: protected */
    public abstract int doRepeatWork() throws InterruptedException;

    public WorkThread(String str) {
        super(str);
        setPriority(1);
    }

    public void run() {
        try {
            doInitial();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        while (this.mIsRunning) {
            try {
                doRepeatWork();
            } catch (Throwable unused) {
                hashCode();
            }
        }
        doRelease();
        Log.e(Constants.TitleMenu.TYPE_FAVORITE, "[Thread quit]" + getName());
    }

    public synchronized void start() {
        if (!this.mIsRunning) {
            this.mIsRunning = true;
            super.start();
        }
    }

    public synchronized void stopThreadAsyn() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            interrupt();
        }
    }

    public synchronized void stopThreadSyn() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            interrupt();
            try {
                join();
            } catch (InterruptedException unused) {
            }
        }
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }
}
