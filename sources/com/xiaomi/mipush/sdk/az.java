package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.xiaomi.channel.commonutils.logger.b;

class az implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aw f11543a;

    az(aw awVar) {
        this.f11543a = awVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.f11543a) {
            Messenger unused = this.f11543a.e = new Messenger(iBinder);
            boolean unused2 = this.f11543a.j = false;
            for (Message send : this.f11543a.i) {
                try {
                    this.f11543a.e.send(send);
                } catch (RemoteException e) {
                    b.a((Throwable) e);
                }
            }
            this.f11543a.i.clear();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Messenger unused = this.f11543a.e = null;
        boolean unused2 = this.f11543a.j = false;
    }
}
