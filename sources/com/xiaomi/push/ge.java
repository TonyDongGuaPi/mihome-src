package com.xiaomi.push;

class ge implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f12748a;
    final /* synthetic */ gb b;

    ge(gb gbVar, String str) {
        this.b = gbVar;
        this.f12748a = str;
    }

    public void run() {
        db.a().a(this.f12748a, true);
    }
}
