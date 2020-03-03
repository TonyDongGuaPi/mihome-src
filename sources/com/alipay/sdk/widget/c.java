package com.alipay.sdk.widget;

class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f1145a;

    c(a aVar) {
        this.f1145a = aVar;
    }

    public void run() {
        if (this.f1145a.e != null && this.f1145a.e.isShowing()) {
            try {
                this.f1145a.l.removeMessages(1);
                this.f1145a.e.dismiss();
            } catch (Exception e) {
                com.alipay.sdk.util.c.a(e);
            }
        }
    }
}
