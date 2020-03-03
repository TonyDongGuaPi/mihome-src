package com.huawei.hms.support.log.a;

import com.huawei.hms.support.log.a.a;

class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f5898a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ a.C0056a e;

    c(a.C0056a aVar, String str, int i, String str2, String str3) {
        this.e = aVar;
        this.f5898a = str;
        this.b = i;
        this.c = str2;
        this.d = str3;
    }

    public void run() {
        this.e.f5896a.a(this.f5898a, this.b, this.c, this.d);
    }
}
