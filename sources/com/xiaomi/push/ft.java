package com.xiaomi.push;

class ft extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ fs f12742a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ft(fs fsVar, String str) {
        super(str);
        this.f12742a = fsVar;
    }

    public void run() {
        try {
            this.f12742a.w.a();
        } catch (Exception e) {
            this.f12742a.c(9, e);
        }
    }
}
