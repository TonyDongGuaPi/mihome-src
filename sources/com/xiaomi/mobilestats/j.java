package com.xiaomi.mobilestats;

import android.content.Context;
import com.xiaomi.mobilestats.common.CrashHandler;
import java.lang.ref.SoftReference;

class j implements Runnable {
    private SoftReference f;

    public j(Context context) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
    }

    public void run() {
        CrashHandler instance = CrashHandler.getInstance();
        Context context = (Context) this.f.get();
        if (context != null) {
            instance.init(context.getApplicationContext());
        }
        Thread.setDefaultUncaughtExceptionHandler(instance);
    }
}
