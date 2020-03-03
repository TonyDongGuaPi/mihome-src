package com.huawei.hms.update.a;

import java.io.File;

class n implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f5915a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ File d;
    final /* synthetic */ l e;

    n(l lVar, int i, int i2, int i3, File file) {
        this.e = lVar;
        this.f5915a = i;
        this.b = i2;
        this.c = i3;
        this.d = file;
    }

    public void run() {
        this.e.f5913a.a(this.f5915a, this.b, this.c, this.d);
    }
}
