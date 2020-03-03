package com.alipay.sdk.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class d extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f1146a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    d(a aVar, Looper looper) {
        super(looper);
        this.f1146a = aVar;
    }

    public void dispatchMessage(Message message) {
        this.f1146a.c();
    }
}
