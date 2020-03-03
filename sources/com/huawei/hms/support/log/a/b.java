package com.huawei.hms.support.log.a;

import android.content.Context;
import com.huawei.hms.support.log.a.a;

class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f5897a;
    final /* synthetic */ String b;
    final /* synthetic */ a.C0056a c;

    b(a.C0056a aVar, Context context, String str) {
        this.c = aVar;
        this.f5897a = context;
        this.b = str;
    }

    public void run() {
        this.c.f5896a.a(this.f5897a, this.b);
    }
}
