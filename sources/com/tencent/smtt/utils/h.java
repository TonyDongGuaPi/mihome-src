package com.tencent.smtt.utils;

import android.widget.Toast;

class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f9207a;

    h(e eVar) {
        this.f9207a = eVar;
    }

    public void run() {
        Toast.makeText(this.f9207a.b, "下载失败，请检查网络", 0).show();
    }
}
