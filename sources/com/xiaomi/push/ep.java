package com.xiaomi.push;

import android.content.Context;

final class ep implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f12723a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ String d;

    ep(Context context, String str, int i, String str2) {
        this.f12723a = context;
        this.b = str;
        this.c = i;
        this.d = str2;
    }

    public void run() {
        eo.c(this.f12723a, this.b, this.c, this.d);
    }
}
