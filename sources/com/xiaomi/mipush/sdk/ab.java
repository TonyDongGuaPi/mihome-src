package com.xiaomi.mipush.sdk;

import android.content.Context;

final class ab implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f11525a;

    ab(Context context) {
        this.f11525a = context;
    }

    public void run() {
        MessageHandleService.c(this.f11525a);
    }
}
