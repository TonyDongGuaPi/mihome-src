package com.unionpay.mobile.android.hce;

final class h extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9567a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    h(f fVar, String str, String str2) {
        this.c = fVar;
        this.f9567a = str;
        this.b = str2;
    }

    public final void run() {
        if (this.c.a(this.f9567a, this.b)) {
            if (this.c.y != null) {
                this.c.y.sendMessageDelayed(this.c.y.obtainMessage(2006, this.f9567a), (long) this.c.i);
            }
        } else if (this.c.y != null) {
            this.c.y.sendMessage(this.c.y.obtainMessage(2005, this.f9567a));
        }
    }
}
