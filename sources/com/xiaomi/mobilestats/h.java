package com.xiaomi.mobilestats;

import android.content.Context;
import java.lang.ref.SoftReference;
import java.util.HashMap;

class h implements Runnable {
    private SoftReference f;
    private HashMap h;
    private String i;
    private String p;

    public h(Context context, String str, String str2, HashMap hashMap) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
        this.i = str;
        this.p = str2;
        this.h = hashMap;
    }

    public void run() {
        Context context = (Context) this.f.get();
        if (context != null) {
            StatService.b(context, this.i, this.p, this.h);
        }
    }
}
