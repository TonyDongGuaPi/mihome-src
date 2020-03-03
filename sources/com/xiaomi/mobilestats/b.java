package com.xiaomi.mobilestats;

import android.content.Context;
import java.lang.ref.SoftReference;
import java.util.HashMap;

class b implements Runnable {
    private SoftReference f;
    private Exception g;
    private HashMap h;

    public b(Context context, Exception exc, HashMap hashMap) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
        this.g = exc;
        this.h = hashMap;
    }

    public void run() {
        Context context = (Context) this.f.get();
        if (context != null) {
            StatService.a(context, this.g, this.h);
        }
    }
}
