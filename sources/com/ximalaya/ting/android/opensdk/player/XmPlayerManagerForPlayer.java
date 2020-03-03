package com.ximalaya.ting.android.opensdk.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayer;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class XmPlayerManagerForPlayer {

    /* renamed from: a  reason: collision with root package name */
    private static volatile XmPlayerManagerForPlayer f2145a;
    private Context b;
    private boolean c = false;
    /* access modifiers changed from: private */
    public IXmPlayer d;
    /* access modifiers changed from: private */
    public CopyOnWriteArrayList<IConnectListener> e = new CopyOnWriteArrayList<>();
    private ServiceConnection f = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName componentName) {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IXmPlayer unused = XmPlayerManagerForPlayer.this.d = IXmPlayer.Stub.asInterface(iBinder);
            if (XmPlayerManagerForPlayer.this.e != null) {
                Iterator it = XmPlayerManagerForPlayer.this.e.iterator();
                while (it.hasNext()) {
                    ((IConnectListener) it.next()).a();
                }
            }
        }
    };

    public interface IConnectListener {
        void a();
    }

    public static XmPlayerManagerForPlayer a(Context context) {
        if (f2145a == null) {
            synchronized (XmPlayerManagerForPlayer.class) {
                if (f2145a == null) {
                    f2145a = new XmPlayerManagerForPlayer(context);
                }
            }
        }
        return f2145a;
    }

    private XmPlayerManagerForPlayer(Context context) {
        this.b = context.getApplicationContext();
    }

    private void b(IConnectListener iConnectListener) {
        if (iConnectListener != null && !this.e.contains(iConnectListener)) {
            this.e.add(iConnectListener);
        }
    }

    public void a(IConnectListener iConnectListener) {
        b(iConnectListener);
        b();
    }

    public static void a() {
        if (f2145a != null && f2145a.b != null && f2145a.f != null && f2145a.d != null && f2145a.d.asBinder() != null && f2145a.d.asBinder().isBinderAlive()) {
            f2145a.b.unbindService(f2145a.f);
        }
    }

    public void b() {
        try {
            this.b.startService(XmPlayerService.getIntent(this.b));
            this.c = this.b.bindService(XmPlayerService.getIntent(this.b), this.f, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void c() {
        if (f2145a != null && f2145a.e != null) {
            f2145a.e.clear();
        }
    }
}
