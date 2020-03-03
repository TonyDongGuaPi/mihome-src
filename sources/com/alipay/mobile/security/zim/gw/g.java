package com.alipay.mobile.security.zim.gw;

import com.alipay.bis.common.service.facade.gw.zim.ZimValidateGwResponse;

class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ZimValidateGwResponse f1057a;
    final /* synthetic */ f b;

    g(f fVar, ZimValidateGwResponse zimValidateGwResponse) {
        this.b = fVar;
        this.f1057a = zimValidateGwResponse;
    }

    public void run() {
        if (this.b.c.mGwListener != null) {
            this.b.c.mGwListener.a(this.f1057a);
        }
    }
}
