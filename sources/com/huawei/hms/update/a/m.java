package com.huawei.hms.update.a;

import com.huawei.hms.update.a.a.c;

class m implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f5914a;
    final /* synthetic */ c b;
    final /* synthetic */ l c;

    m(l lVar, int i, c cVar) {
        this.c = lVar;
        this.f5914a = i;
        this.b = cVar;
    }

    public void run() {
        this.c.f5913a.a(this.f5914a, this.b);
    }
}
