package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiTinyDataClient;
import java.util.concurrent.ScheduledFuture;

class ak implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ MiTinyDataClient.a.C0084a f11530a;

    ak(MiTinyDataClient.a.C0084a aVar) {
        this.f11530a = aVar;
    }

    public void run() {
        if (this.f11530a.f11518a.size() != 0) {
            this.f11530a.b();
        } else if (this.f11530a.d != null) {
            this.f11530a.d.cancel(false);
            ScheduledFuture unused = this.f11530a.d = null;
        }
    }
}
