package com.xiaomi.push.service.receivers;

import android.content.Context;

class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f12938a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ NetworkStatusReceiver f359a;

    a(NetworkStatusReceiver networkStatusReceiver, Context context) {
        this.f359a = networkStatusReceiver;
        this.f12938a = context;
    }

    public void run() {
        this.f359a.a(this.f12938a);
    }
}
