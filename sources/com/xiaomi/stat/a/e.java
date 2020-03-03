package com.xiaomi.stat.a;

import java.util.concurrent.Callable;

class e implements Callable<k> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b[] f23002a;
    final /* synthetic */ c b;

    e(c cVar, b[] bVarArr) {
        this.b = cVar;
        this.f23002a = bVarArr;
    }

    /* renamed from: a */
    public k call() throws Exception {
        return this.b.b(this.f23002a);
    }
}
