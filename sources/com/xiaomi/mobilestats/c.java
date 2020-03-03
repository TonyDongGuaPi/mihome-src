package com.xiaomi.mobilestats;

import android.content.Context;
import java.lang.ref.SoftReference;

class c implements Runnable {
    private SoftReference f;
    private String i;
    private String j;

    public c(Context context, String str, String str2) {
        this.f = new SoftReference(context == null ? null : context.getApplicationContext());
        this.i = str;
        this.j = str2;
    }

    public void run() {
        Context context = (Context) this.f.get();
        if (context != null) {
            StatService.a(context, this.i, this.j);
        }
    }
}
