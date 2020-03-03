package com.xiaomi.clientreport.manager;

import com.xiaomi.push.bk;

class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f10082a;

    c(a aVar) {
        this.f10082a = aVar;
    }

    public void run() {
        this.f10082a.b.execute(new bk(this.f10082a.e, this.f10082a.h));
    }
}
