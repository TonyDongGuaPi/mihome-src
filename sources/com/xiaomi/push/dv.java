package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;

class dv implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f12701a;
    final /* synthetic */ Intent b;
    final /* synthetic */ du c;

    dv(du duVar, Context context, Intent intent) {
        this.c = duVar;
        this.f12701a = context;
        this.b = intent;
    }

    public void run() {
        this.c.b(this.f12701a, this.b);
    }
}
