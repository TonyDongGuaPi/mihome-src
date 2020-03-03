package com.tencent.smtt.utils;

import android.widget.Toast;

class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f9205a;

    f(e eVar) {
        this.f9205a = eVar;
    }

    public void run() {
        Toast.makeText(this.f9205a.b, "下载成功", 0).show();
        this.f9205a.c.setVisibility(4);
        this.f9205a.f.a(this.f9205a.d, this.f9205a.f9204a, this.f9205a.b, d.c);
    }
}
