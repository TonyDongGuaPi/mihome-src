package com.xiaomi.mobilestats;

import android.content.Context;
import java.lang.ref.SoftReference;
import java.util.HashMap;

class g implements Runnable {
    private SoftReference f;
    private HashMap h;
    private boolean m;
    private String n;
    private String o;

    public g(Context context, boolean z, String str, HashMap hashMap, String str2) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
        this.m = z;
        this.n = str;
        this.h = hashMap;
        this.o = str2;
    }

    public void run() {
        Context context = (Context) this.f.get();
        if (context != null) {
            StatService.a(context, this.m, this.n, this.h, this.o);
        }
    }
}
