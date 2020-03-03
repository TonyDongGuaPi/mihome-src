package com.xiaomi.clientreport.manager;

import com.xiaomi.push.bk;

class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f10081a;

    b(a aVar) {
        this.f10081a = aVar;
    }

    public void run() {
        this.f10081a.b.execute(new bk(this.f10081a.e, this.f10081a.g));
    }
}
