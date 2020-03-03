package com.unionpay.mobile.android.widgets;

import android.os.Message;

final class ar extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f9777a;
    final /* synthetic */ ap b;

    ar(ap apVar, int i) {
        this.b = apVar;
        this.f9777a = i;
    }

    public final void run() {
        int i;
        if (this.b.p != null) {
            long currentTimeMillis = System.currentTimeMillis() + ((long) (this.f9777a * 1000));
            while (true) {
                long currentTimeMillis2 = System.currentTimeMillis();
                if (currentTimeMillis2 > currentTimeMillis || (i = (int) (((currentTimeMillis - currentTimeMillis2) + ((long) this.f9777a)) / 1000)) <= 0) {
                    this.b.p.sendEmptyMessage(1);
                } else {
                    Message obtain = Message.obtain();
                    obtain.what = 0;
                    obtain.arg1 = i;
                    this.b.p.sendMessage(obtain);
                    try {
                        sleep(1000);
                    } catch (InterruptedException unused) {
                        this.b.p.sendEmptyMessage(1);
                        return;
                    }
                }
            }
            this.b.p.sendEmptyMessage(1);
        }
    }
}
