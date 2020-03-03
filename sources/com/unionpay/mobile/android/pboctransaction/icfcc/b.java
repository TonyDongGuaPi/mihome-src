package com.unionpay.mobile.android.pboctransaction.icfcc;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.gov.pbc.tsm.client.mobile.android.bank.service.a;

final class b implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9645a;

    b(a aVar) {
        this.f9645a = aVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            a unused = this.f9645a.c = a.C0023a.a(iBinder);
            if (this.f9645a.d != null) {
                this.f9645a.d.a();
            }
        } catch (Exception unused2) {
            if (this.f9645a.d != null) {
                this.f9645a.d.b();
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        a unused = this.f9645a.c = null;
    }
}
