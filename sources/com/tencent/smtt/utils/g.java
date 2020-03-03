package com.tencent.smtt.utils;

import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;

class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f9206a;
    final /* synthetic */ e b;

    g(e eVar, int i) {
        this.b = eVar;
        this.f9206a = i;
    }

    public void run() {
        TextView textView = this.b.e;
        textView.setText("已下载" + this.f9206a + Operators.MOD);
    }
}
