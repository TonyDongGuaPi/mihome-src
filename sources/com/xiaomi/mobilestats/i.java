package com.xiaomi.mobilestats;

import android.content.Context;
import java.lang.ref.SoftReference;
import java.util.HashMap;

class i implements Runnable {
    private SoftReference f;
    private HashMap h;
    private String j;
    private String q;

    public i(Context context, String str, String str2, HashMap hashMap) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
        this.q = str;
        this.j = str2;
        this.h = hashMap;
    }

    public void run() {
        Context context = (Context) this.f.get();
        if (context != null) {
            StatService.c(context, this.q, this.j, this.h);
        }
    }
}
