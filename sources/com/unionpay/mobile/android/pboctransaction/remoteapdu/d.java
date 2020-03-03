package com.unionpay.mobile.android.pboctransaction.remoteapdu;

import android.os.RemoteException;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.tsm.connect.IInitCallback;

final class d extends IInitCallback.Stub {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9653a;

    d(a aVar) {
        this.f9653a = aVar;
    }

    public final void initFailed() throws RemoteException {
        k.a("plugin-tsm", "mInitCallback.initFailed()");
        if (this.f9653a.e != null) {
            this.f9653a.e.removeMessages(3000);
        }
        if (this.f9653a.f9650a != null) {
            this.f9653a.f9650a.b();
        }
    }

    public final void initSucceed() throws RemoteException {
        k.a("plugin-tsm", "mInitCallback.initSucceed()");
        if (this.f9653a.e != null) {
            this.f9653a.e.removeMessages(3000);
        }
        if (this.f9653a.f9650a != null) {
            this.f9653a.f9650a.a();
        }
    }
}
