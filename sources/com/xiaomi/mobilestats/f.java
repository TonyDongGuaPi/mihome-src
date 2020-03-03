package com.xiaomi.mobilestats;

import android.content.Context;
import java.lang.ref.SoftReference;

class f implements Runnable {
    private SoftReference f;

    public f(Context context) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
    }

    public void run() {
        Context context = (Context) this.f.get();
        if (context != null) {
            StatService.b(context);
        }
    }
}
