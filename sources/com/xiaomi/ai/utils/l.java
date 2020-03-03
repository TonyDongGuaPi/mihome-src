package com.xiaomi.ai.utils;

import com.xiaomi.ai.HTTPCallback;
import java.util.Map;

class l implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ HTTPCallback f9956a;
    final /* synthetic */ String b;
    final /* synthetic */ Map c;
    final /* synthetic */ String d;
    final /* synthetic */ k e;

    l(k kVar, HTTPCallback hTTPCallback, String str, Map map, String str2) {
        this.e = kVar;
        this.f9956a = hTTPCallback;
        this.b = str;
        this.c = map;
        this.d = str2;
    }

    public void run() {
        f.a(this.f9956a, this.b, this.c, this.d);
    }
}
