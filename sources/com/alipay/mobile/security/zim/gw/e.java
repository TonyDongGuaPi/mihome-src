package com.alipay.mobile.security.zim.gw;

import com.alipay.bis.common.service.facade.gw.zim.ZimInitGwResponse;

class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ZimInitGwResponse f1055a;
    final /* synthetic */ d b;

    e(d dVar, ZimInitGwResponse zimInitGwResponse) {
        this.b = dVar;
        this.f1055a = zimInitGwResponse;
    }

    public void run() {
        if (this.b.b.mGwListener != null) {
            this.b.b.mGwListener.a(this.f1055a);
        }
    }
}
