package com.xiaomi.mobilestats;

import android.content.Context;
import java.lang.ref.SoftReference;
import java.util.HashMap;

class d implements Runnable {
    private SoftReference f;
    private HashMap h;
    private int k;
    private String l;

    public d(Context context, Object obj, String str, HashMap hashMap) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
        this.k = obj.hashCode();
        this.l = str;
        this.h = hashMap;
    }

    public void run() {
        Context context = (Context) this.f.get();
        if (context != null) {
            StatService.b(context, this.k, this.l, this.h);
        }
    }
}
