package com.alipay.mobile.security.zim.a;

import com.alipay.mobile.security.bio.utils.BioLog;

class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d f1050a;

    h(d dVar) {
        this.f1050a = dVar;
    }

    public void run() {
        try {
            this.f1050a.j.cancel();
        } catch (Throwable th) {
            BioLog.w(th);
        }
    }
}
