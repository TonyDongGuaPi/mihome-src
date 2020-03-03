package com.alipay.mobile.security.zim.a;

import com.alipay.mobile.security.bio.utils.BioLog;

class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d f1049a;

    g(d dVar) {
        this.f1049a = dVar;
    }

    public void run() {
        try {
            this.f1049a.j.cancel();
        } catch (Throwable th) {
            BioLog.w(th);
        }
    }
}
