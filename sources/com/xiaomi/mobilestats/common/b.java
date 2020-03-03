package com.xiaomi.mobilestats.common;

import android.os.Process;

class b implements Runnable {
    final /* synthetic */ a z;

    b(a aVar) {
        this.z = aVar;
    }

    public void run() {
        Process.killProcess(Process.myPid());
    }
}
