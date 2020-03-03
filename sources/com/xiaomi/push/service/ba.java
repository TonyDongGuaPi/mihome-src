package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.xiaomi.channel.commonutils.logger.b;

class ba implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ay f12898a;

    ba(ay ayVar) {
        this.f12898a = ayVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.f12898a) {
            Messenger unused = this.f12898a.b = new Messenger(iBinder);
            boolean unused2 = this.f12898a.f311b = false;
            for (Message send : ay.a(this.f12898a)) {
                try {
                    ay.a(this.f12898a).send(send);
                } catch (RemoteException e) {
                    b.a((Throwable) e);
                }
            }
            ay.a(this.f12898a).clear();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Messenger unused = this.f12898a.b = null;
        boolean unused2 = this.f12898a.f311b = false;
    }
}
