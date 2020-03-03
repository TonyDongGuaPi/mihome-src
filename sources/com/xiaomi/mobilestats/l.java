package com.xiaomi.mobilestats;

import android.content.Context;
import java.lang.ref.SoftReference;

class l implements Runnable {
    private SoftReference f;

    public l(Context context) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
    }

    public void run() {
        Context context = (Context) this.f.get();
        if (context != null) {
            StatService.a(context);
        }
    }
}
