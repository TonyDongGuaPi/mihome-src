package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.b;

final class aa implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f11524a;
    final /* synthetic */ Intent b;

    aa(Context context, Intent intent) {
        this.f11524a = context;
        this.b = intent;
    }

    public void run() {
        try {
            this.f11524a.startService(this.b);
        } catch (Exception e) {
            b.a(e.getMessage());
        }
    }
}
