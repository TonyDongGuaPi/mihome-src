package com.unionpay.mobile.android.pboctransaction.remoteapdu;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.tsm.connect.IRemoteApdu;

final class c implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9652a;

    c(a aVar) {
        this.f9652a = aVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        k.a("plugin-tsm", "mConnection.onServiceConnected()");
        if (this.f9652a.e != null) {
            this.f9652a.e.removeMessages(3000);
        }
        try {
            boolean unused = this.f9652a.f = true;
            IRemoteApdu unused2 = this.f9652a.b = IRemoteApdu.Stub.asInterface(iBinder);
            if (this.f9652a.e != null) {
                this.f9652a.e.sendMessageDelayed(Message.obtain(this.f9652a.e, 3000), 8000);
            }
            this.f9652a.b.registerCallback(this.f9652a.i);
            this.f9652a.b.init();
        } catch (Exception unused3) {
            if (this.f9652a.f9650a != null) {
                this.f9652a.f9650a.b();
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        k.a("plugin-tsm", "mConnection.onServiceDisconnected()");
        if (this.f9652a.e != null) {
            this.f9652a.e.removeMessages(3000);
        }
        IRemoteApdu unused = this.f9652a.b = null;
        if (this.f9652a.f9650a != null) {
            this.f9652a.f9650a.b();
        }
    }
}
