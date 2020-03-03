package com.ximalaya.ting.android.player;

import android.os.HandlerThread;
import android.os.Process;

public class PriorityHandlerThread extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    private final int f2280a;

    public PriorityHandlerThread(String str, int i) {
        super(str);
        this.f2280a = i;
    }

    public void run() {
        Process.setThreadPriority(this.f2280a);
        super.run();
    }
}
