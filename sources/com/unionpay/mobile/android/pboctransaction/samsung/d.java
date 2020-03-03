package com.unionpay.mobile.android.pboctransaction.samsung;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.unionpay.client3.tsm.ITsmConnection;
import com.unionpay.mobile.android.utils.k;

final class d implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9656a;

    d(b bVar) {
        this.f9656a = bVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        k.a("plugin-clientV3", "startSamsungService onServiceConnected");
        try {
            ITsmConnection unused = this.f9656a.c = ITsmConnection.Stub.asInterface(iBinder);
            this.f9656a.f.removeMessages(1);
            this.f9656a.a(true);
        } catch (Exception unused2) {
            this.f9656a.a(false);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        k.a("plugin-clientV3", "startSamsungService onServiceDisconnected");
        ITsmConnection unused = this.f9656a.c = null;
        this.f9656a.f.removeMessages(1);
        this.f9656a.a(false);
    }
}
