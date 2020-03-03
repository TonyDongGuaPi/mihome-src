package com.tencent.wxop.stat;

import android.content.Context;

final class o implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9334a;

    o(Context context) {
        this.f9334a = context;
    }

    public final void run() {
        StatServiceImpl.i(this.f9334a);
    }
}
