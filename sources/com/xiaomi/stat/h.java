package com.xiaomi.stat;

import android.text.TextUtils;
import com.xiaomi.stat.a.l;

class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f23069a;
    final /* synthetic */ e b;

    h(e eVar, String str) {
        this.b = eVar;
        this.f23069a = str;
    }

    public void run() {
        if (b.a() && !TextUtils.equals(b.h(), this.f23069a)) {
            b.b(this.f23069a);
            if (this.b.g()) {
                this.b.a(l.a(this.f23069a));
            }
        }
    }
}
