package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

final class h extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9179a;
    final /* synthetic */ String b;
    final /* synthetic */ ValueCallback c;

    h(Context context, String str, ValueCallback valueCallback) {
        this.f9179a = context;
        this.b = str;
        this.c = valueCallback;
    }

    public void run() {
        bt a2 = bt.a();
        a2.a(this.f9179a);
        new Handler(Looper.getMainLooper()).post(new i(this, a2.b() ? a2.c().a(this.f9179a, this.b) : false));
    }
}
