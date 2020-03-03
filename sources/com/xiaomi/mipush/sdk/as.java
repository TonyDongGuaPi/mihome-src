package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;

final class as implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f11535a;
    final /* synthetic */ Intent b;

    as(Context context, Intent intent) {
        this.f11535a = context;
        this.b = intent;
    }

    public void run() {
        PushMessageHandler.b(this.f11535a, this.b);
    }
}
