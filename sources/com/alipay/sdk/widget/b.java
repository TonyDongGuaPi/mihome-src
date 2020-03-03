package com.alipay.sdk.widget;

import com.alipay.sdk.util.c;
import com.alipay.sdk.widget.a;

class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f1144a;

    b(a aVar) {
        this.f1144a = aVar;
    }

    public void run() {
        if (this.f1144a.e == null) {
            a.C0031a unused = this.f1144a.e = new a.C0031a(this.f1144a.f);
            this.f1144a.e.setCancelable(this.f1144a.k);
        }
        try {
            if (!this.f1144a.e.isShowing()) {
                this.f1144a.e.show();
                this.f1144a.l.sendEmptyMessageDelayed(1, 15000);
            }
        } catch (Exception e) {
            c.a(e);
        }
    }
}
