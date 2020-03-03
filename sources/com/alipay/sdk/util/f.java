package com.alipay.sdk.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.android.app.IAlixPay;

class f implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f1129a;

    f(e eVar) {
        this.f1129a = eVar;
    }

    public void onServiceDisconnected(ComponentName componentName) {
        IAlixPay unused = this.f1129a.d = null;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.f1129a.e) {
            IAlixPay unused = this.f1129a.d = IAlixPay.Stub.asInterface(iBinder);
            this.f1129a.e.notify();
        }
    }
}
