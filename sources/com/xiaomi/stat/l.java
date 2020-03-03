package com.xiaomi.stat;

import android.text.TextUtils;
import com.xiaomi.stat.b.g;
import com.xiaomi.stat.d.m;

class l implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f23073a;
    final /* synthetic */ String b;
    final /* synthetic */ e c;

    l(e eVar, boolean z, String str) {
        this.c = eVar;
        this.f23073a = z;
        this.b = str;
    }

    public void run() {
        if (!m.a()) {
            b.c(this.f23073a);
            g.a().a(this.f23073a);
        }
        if (b.e() && !TextUtils.isEmpty(this.b)) {
            b.a(this.b);
            g.a().a(this.b);
        }
    }
}
