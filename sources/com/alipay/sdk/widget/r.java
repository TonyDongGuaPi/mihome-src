package com.alipay.sdk.widget;

import android.view.View;

class r implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ View f1157a;
    final /* synthetic */ q b;

    r(q qVar, View view) {
        this.b = qVar;
        this.f1157a = view;
    }

    public void run() {
        this.f1157a.setEnabled(true);
    }
}
